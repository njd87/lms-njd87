package lms.koika

import lms.core.stub._
import lms.core.virtualize
import lms.macros.SourceContext

@virtualize
class InterpCTest extends TutorialFunSuite {
  val under = "interpc_"

  override def exec(label: String, code: String, suffix: String = "c") =
    super.exec(label, code, suffix)

  class stateT
  trait InterpC extends Dsl with lms.thirdparty.CLibs {
    // TODO: the file state.h should be defined to contain those operations
    def newState(): Rep[stateT] =
      newStruct[stateT]("state_t")
    def state_pc(s: Rep[stateT]): Rep[Int] =
      libFunction[Int]("state_pc", Unwrap(s))(Seq(0), Seq(), Set[Int]())
    def state_reg(s: Rep[stateT], i: Rep[Int]): Rep[Int] =
      libFunction[Int]("state_reg", Unwrap(s), Unwrap(i))(Seq(0,1), Seq(), Set[Int]())
    def set_state_pc(s: Rep[stateT], pc: Rep[Int]): Rep[Unit] =
      libFunction[Unit]("set_state_pc", Unwrap(s), Unwrap(pc))(Seq(0,1), Seq(0), Set[Int]())
    def set_state_reg(s: Rep[stateT], i: Rep[Int], v: Rep[Int]): Rep[Unit] =
      libFunction[Unit]("set_state_reg", Unwrap(s), Unwrap(i), Unwrap(v))(Seq(0,1,2), Seq(0), Set[Int]())

    abstract sealed class Instruction
    case class Add(rd: Int, rs1: Int, rs2: Int) extends Instruction
    case class Branch(rs: Int, target: Int) extends Instruction

    type State = (
      Array[Instruction],
      Rep[stateT]
    )

    val prog: Array[Instruction] = List(Add(0, 0, 0), Branch(0, 0)).toArray

    def id(a: Rep[Int]) = a

    def step_aux(ins: Array[Instruction], s: Rep[stateT], pc: Int): Unit = {
      set_state_pc(s, pc+1)
      ins(pc) match {
        case Add(rd, rs1, rs2) =>
          set_state_reg(s, rd, state_reg(s, rs1) + state_reg(s, rs2))
        case Branch(rs, target) => {
          if (state_reg(s, rs) == unit(0))
            set_state_pc(s, target)
        }
      }
    }

    def step(ins: Array[Instruction], s: Rep[stateT]): Unit = {
      val pc = state_pc(s)
      for (i <- (0 until ins.size): Range) {
        if (pc == unit(i))
          step_aux(ins, s, i)
      }
    }
  }

  abstract class DslDriverX[A:Manifest,B:Manifest] extends DslDriverC[A,B] { q =>
    override val codegen = new DslGenC with lms.thirdparty.CCodeGenLibs {
      val IR: q.type = q

      registerHeader("\"state.h\"")
    }
  }

  test("struct 1") {
    val snippet = new DslDriverX[Int,Int] with InterpC {
      class myStructT
      def snippet(a: Rep[Int]) = {
        val s = newStruct[myStructT]("myStruct_t")
        libFunction[Int]("myStruct_getField", Unwrap(s))(Seq(0), Seq(), Set[Int]())
      }
    }
    exec("struct_1", snippet.code)
  }

  test("interp 1") {
    val snippet = new DslDriverX[Int,Int] with InterpC {
      def snippet(a: Rep[Int]) = id(id(a))
    }
    exec("1", snippet.code)
  }

  test("interp 2") {
    val snippet = new DslDriverX[stateT,stateT] with InterpC {
      def snippet(s: Rep[stateT]) = {
        step_aux(prog, s, 0)
        s
      }
    }
    exec("2", snippet.code)
  }

  test("interp 3") {
    val snippet = new DslDriverX[stateT,stateT] with InterpC {
      def snippet(s: Rep[stateT]) = {
        step(prog, s)
        s
      }
    }
    exec("3", snippet.code)
  }

  test("interp 4") {
    val snippet = new DslDriverX[stateT,stateT] with InterpC {
      def snippet(s: Rep[stateT]) = {
        set_state_pc(s, 0)
        step(prog, s)
        s
      }
    }
    // TODO: this no longer optimizes.
    exec("4", snippet.code)
  }

  test("interp 5") {
    val snippet = new DslDriverX[stateT,stateT] with InterpC {
      def snippet(s: Rep[stateT]) = {
        set_state_pc(s, 0)
        step(prog, s)
        step(prog, s)
        step(prog, s)
        step(prog, s)
        s
      }
    }
    // this program should simplify the branches, but it does not
    exec("5", snippet.code)
  }
}
