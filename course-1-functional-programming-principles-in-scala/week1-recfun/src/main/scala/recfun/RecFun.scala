package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(c == r || c == 0) 1
    else pascal(c, r-1) + pascal(c-1, r-1)
  }
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def charsIter(chars: List[Char], parCount: Int): Boolean = {
      if(parCount < 0) return false
      if(chars.isEmpty) return parCount == 0
      val newCount = 
        if(chars.head == '(') parCount + 1
        else if (chars.head == ')') parCount - 1
        else parCount
      charsIter(chars.tail, newCount)
    }

    charsIter(chars, 0)
  }

  /**
   * Exercise 3
   */
  // In each particular case the first coin is either part of the solution or it isn't
  // if it is part of the solution, we use it and keep it to use on the smaller amount
  // if it is not, we remove it from the set of possible coins and consider the money
  // against the remaining set.
  // Continue until we hit our base cases (money is 0 or negative, or we have no more coins)
  def countChange(money: Int, coins: List[Int]): Int = {
    if(money == 0) 1
    else if(money < 0) 0
    else if(coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
