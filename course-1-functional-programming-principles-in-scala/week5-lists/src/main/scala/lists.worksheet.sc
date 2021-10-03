def flatten(xs: Any): List[Any] = {
  xs match {
    case Nil     => Nil
    case y :: ys => flatten(y) ++ flatten(ys)
    case _       => xs :: Nil
  }
}

flatten(List(List(2, 1), 2, List(3, List(5, 8))))

// def squareList(xs: List[Int]): List[Int] = xs match {
//   case Nil     => Nil
//   case y :: ys => y * y :: squareList(ys)
// }

// squareList(List(1, 2, 3))

def squareList(xs: List[Int]): List[Int] =
  xs.map(x => x * x)

squareList(List(1, 2, 3))

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case y :: ys => {
    val (listWithRepeatingElement, rest) = xs.span(x => x == y)
    listWithRepeatingElement :: pack(rest)
  }
}

val elems = List("a", "a", "a", "b", "c", "c", "a")
pack(elems)
// expect List(List("a", "a", "a"), List("b"), List("c", "c"), List("a"))

def encode[T](list: List[T]): List[(T, Int)] = {
  // val packed = pack(list)
  // def loop[T](xs: List[List[T]]): List[(T, Int)] = xs match {
  //   case Nil => Nil
  //   case y :: ys => {
  //     (y.head, y.length) :: loop(ys)
  //   }
  // }
  // loop(packed)

  // better
  pack(list).map(x => (x.head, x.length))
}
encode(elems)

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  xs.foldRight(List[U]())((x: T, acc: List[U]) => f(x) :: acc)

def lengthFun[T](xs: List[T]): Int = {
  xs.foldRight(0)((x: T, acc: Int) => acc + 1)
}

def list = List(1, 2, 3, 4)
def square(x: Int): Int = x * x
square(3)
list.map(x => x * x)
mapFun(list, square)

list.foldRight(0)(_ + _)

list.length
lengthFun(list)
