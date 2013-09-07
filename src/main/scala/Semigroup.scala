import scalaz._
import Scalaz._

object AddMore extends Semigroup[Int] {
  def append(a: Int, b: => Int): Int = a + b + 1
}

object SemigroupDemo {
  def main(args: Array[String]) = {
    println(AddMore.append(1, 2))
    println(AddMore.apply.map(1)(x => 3))
    println(AddMore.semigroupLaw.associative(1, 2, 3))
  }
}
