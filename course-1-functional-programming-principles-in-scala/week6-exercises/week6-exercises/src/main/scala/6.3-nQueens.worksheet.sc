def isSafe(col: Int, queens: List[Int]): Boolean = {
  val row = queens.length
  (0 until queens.length)
    .zip(queens)
    .forall((r, c) =>
      c != col &&
        (r - c) != (row - col) &&
        (r + c) != (row + col)
    )
}

isSafe(2, List(0)) // expect true
isSafe(2, List(1)) // expect false
