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
    exec("stub", snippet.code)
  }
}
