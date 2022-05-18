package lms.koika

import lms.core.stub._
import lms.core.virtualize
import lms.macros.SourceContext

@virtualize
class InterpC0Test extends TutorialFunSuite {
  val under = "interpc0_"

  override def exec(label: String, code: String, suffix: String = "c") =
    super.exec(label, code, suffix)

  override def check(label: String, code: String, suffix: String = "c") =
    super.check(label, code, suffix)

  trait InterpC extends Dsl {
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

  test("interp 1") {
    val snippet = new DslDriverC[Int,Int] with InterpC {
      def snippet(a: Rep[Int]) = id(id(a))
    }
    check("1", snippet.code)
  }

  test("interp 2") {
    val snippet = new DslDriverC[Array[Int],Array[Int]] with InterpC {
      def snippet(pc_reg: Rep[Array[Int]]) = {
        step_aux(prog, pc_reg, 0)
        pc_reg
      }
    }
    check("2", snippet.code)
  }

  test("interp 3") {
    val snippet = new DslDriverC[Array[Int],Array[Int]] with InterpC {
      def snippet(pc_reg: Rep[Array[Int]]) = {
        step(prog, pc_reg)
        pc_reg
      }
    }
    check("3", snippet.code)
  }

  test("interp 4") {
    val snippet = new DslDriverC[Array[Int],Array[Int]] with InterpC {
      def snippet(pc_reg: Rep[Array[Int]]) = {
        pc_reg(0) = 0
        step(prog, pc_reg)
        pc_reg
      }
    }
    check("4", snippet.code)
  }

  test("interp 5") {
    val snippet = new DslDriverC[Array[Int],Array[Int]] with InterpC {
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
    check("5", snippet.code)
  }
}
