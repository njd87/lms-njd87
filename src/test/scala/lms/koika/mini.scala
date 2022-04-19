package lms.koika

import lms.core.stub._
import lms.core.virtualize
import lms.macros.SourceContext

@virtualize
class MiniTest extends TutorialFunSuite {
  val under = "mini_"

  test("mini stub") {
    val snippet = new DslDriver[Int,Int] {
      def f(a: Rep[Int]): Rep[Int] = if (a==0) 0 else (a+1)
      def snippet(a: Rep[Int]) = f(f(f(a)))
    }
    check("stub", snippet.code)
  }

  test("mini stub 2") {
    val snippet = new DslDriver[Int,Int] {
      def snippet(a: Rep[Int]) = {
        val pc = var_new(0) // vs var_new(a)
        val state = var_new(0)
        for (i <- (0 until 10):Range) {
          if (pc == 0) {
            state += 1
            pc = 1
          } else if (pc == 1) {
            state -= 1
            pc = 2
          } else {
            state += 1
            pc = 0
          }
        }
        state
      }
    }
    exec("stub2", snippet.code)
  }
}
