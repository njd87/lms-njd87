/*****************************************
Emitting Generated Code
*******************************************/
class Snippet() extends (Array[Int] => Array[Int]) {
  def apply(x0: Array[Int]): Array[Int] = {
    x0(0) = 0
    x0(1) = 1
    if (x0(0) == 0) {
      x0(0) = 1
    }
    if (x0(0) == 1) {
      x0(0) = 2
    }
    if (x0(0) == 2) {
      x0(0) = 3
    }
    x0
  }
}
/*****************************************
End of Generated Code
*******************************************/
