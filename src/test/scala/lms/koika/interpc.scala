package lms.koika

import lms.core.stub._
import lms.core.virtualize
import lms.macros.SourceContext

@virtualize
class InterpCTest extends TutorialFunSuite {
  val under = "interpc_"

  override def exec(label: String, code: String, suffix: String = "c") =
    super.exec(label, code, suffix)

  trait InterpC extends Dsl with lms.thirdparty.CLibs {
    abstract sealed class Instruction
    case class Add(rd: Int, rs1: Int, rs2: Int) extends Instruction
    case class Branch(rs: Int, target: Int) extends Instruction

    type State = (
      Array[Instruction],
      Rep[Array[Int]] /* pc+registers */
    )

    val prog: Array[Instruction] = List(Add(1, 1, 1), Branch(1, 0)).toArray

    def id(a: Rep[Int]) = a

    def step_aux(ins: Array[Instruction], pc_reg: Rep[Array[Int]], pc: Int): Unit = {
      pc_reg(0) = pc+1
      ins(pc) match {
        case Add(rd, rs1, rs2) =>
          pc_reg(rd) = pc_reg(rs1) + pc_reg(rs2)
        case Branch(rs, target) => {
          if (pc_reg(rs) == unit(0))
            pc_reg(0) = target
        }
      }
    }

    def step(ins: Array[Instruction], pc_reg: Rep[Array[Int]]): Unit = {
      val pc = pc_reg(0)
      for (i <- (0 until ins.size): Range) {
        if (pc == unit(i))
          step_aux(ins, pc_reg, i)
      }
    }
  }

  abstract class DslDriverX[A:Manifest,B:Manifest] extends DslDriverC[A,B] { q =>
    override val codegen = new DslGenC with lms.thirdparty.CCodeGenLibs {
      val IR: q.type = q
    }
  }

  test("interp 1") {
    val snippet = new DslDriverX[Int,Int] with InterpC {
      def snippet(a: Rep[Int]) = id(id(a))
    }
    exec("1", snippet.code)
  }

  test("interp 2") {
    val snippet = new DslDriverX[Array[Int],Array[Int]] with InterpC {
      def snippet(pc_reg: Rep[Array[Int]]) = {
        step_aux(prog, pc_reg, 0)
        pc_reg
      }
    }
    exec("2", snippet.code)
  }

  test("interp 3") {
    val snippet = new DslDriverX[Array[Int],Array[Int]] with InterpC {
      def snippet(pc_reg: Rep[Array[Int]]) = {
        step(prog, pc_reg)
        pc_reg
      }
    }
    exec("3", snippet.code)
  }

  test("interp 4") {
    val snippet = new DslDriverX[Array[Int],Array[Int]] with InterpC {
      def snippet(pc_reg: Rep[Array[Int]]) = {
        pc_reg(0) = 0
        step(prog, pc_reg)
        pc_reg
      }
    }
    exec("4", snippet.code)
  }

  test("interp 5") {
    val snippet = new DslDriverX[Array[Int],Array[Int]] with InterpC {
      def snippet(pc_reg: Rep[Array[Int]]) = {
        pc_reg(0) = 0
        step(prog, pc_reg)
        step(prog, pc_reg)
        step(prog, pc_reg)
        step(prog, pc_reg)
        pc_reg
      }
    }
    // this program should simplify the branches, but it does not
    exec("5", snippet.code)
  }
}
