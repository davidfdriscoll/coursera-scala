1 + 1

// To list all combinations of numbers x and y where x is drawn from 1..M and y is drawn from 1..N:

def allCombinations(M: Int, N: Int): Seq[(Int, Int)] =
  (1 to M).flatMap(x => (1 to N).map(y => (x, y)))

allCombinations(2, 3)

// A number n is prime if the only divisors of n are 1 and n itself
// What is a high-level way to write a test for primality of numbers?
// For once, value conciseness over efficiency

def isPrime(n: Int): Boolean =
//  (2 to (n - 1)).filter(n % _ == 0).length == 0
  (2 until n).forall(n % _ != 0)

isPrime(3)
isPrime(4)
isPrime(5)
isPrime(6)
isPrime(121)

// write a version of scalar product that makes use of a for

// def scalarProduct(xs: List[Double], ys: List[Double]): Double = {
//   (for i <- 0 until xs.length
//   yield xs(i) * ys(i))
//     .foldRight(0.0)(_ + _)
// }

def scalarProduct(xs: List[Double], ys: List[Double]): Double = {
  (for (x, y) <- xs.zip(ys) yield x * y).sum
}

val xs = List(1.0, 2.0, 3.0)
val ys = List(2.0, 4.0, 6.0)
scalarProduct(xs, ys)
