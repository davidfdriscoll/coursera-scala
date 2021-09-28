class Coder(words: List[String]) {
  // "words" presumably is the list of words in the dictionary
  val mnemonics = Map(
    '2' -> "ABC",
    '3' -> "DEF",
    '4' -> "GHI",
    '5' -> "JKL",
    '6' -> "MNO",
    '7' -> "PQRS",
    '8' -> "TUV",
    '9' -> "WXYZ"
  )

  // My initial implementations are commented out; the solutions are below

  /** Maps a letter to the digit it represents, e.g. Map('A' -> '2', 'B' -> 2,
    * ... )
    */
  val charCode: Map[Char, Char] = {
    // mnemonics.flatMap(map => {
    //   val (digit, letters) = map
    //   letters.map(letter => letter -> digit)
    // })
    for {
      (digit, str) <- mnemonics
      ltr <- str
    } yield ltr -> digit
  }

  /** Maps a word to the digit string it can represent, e.g. wordCode("CAB") =
    * "222"
    */
  def wordCode(word: String): String = {
    // word.toUpperCase.toList.map(letter => charCode(letter)).mkString
    word.toUpperCase.map(charCode)
  }

  /** Maps a digit string to all words in the dictionary that represent it, e.g.
    * Map("47" -> List("is"), "322" -> List("dab", "cab", "fab"))
    */
  val wordsForNum: Map[String, List[String]] = {
    // words.groupBy(word => wordCode(word))
    words.groupBy(wordCode).withDefaultValue(Nil)
  }

  /** All ways to encode a number as a list of words, e.g. encode("7225247386")
    * = Set(List("Scala","is","fun"), ...)
    */
  // def encode(number: String): Set[List[String]] = {
  //   if (number.length == 0) Set(List())
  //   else
  //     for {
  //       (digits, words) <- wordsForNum.toSet
  //       if number.startsWith(digits)
  //       listOfMnemonics <- handleDigits(digits, words, number)
  //     } yield listOfMnemonics
  // }

  // def handleDigits(
  //     digits: String,
  //     words: List[String],
  //     number: String
  // ): Set[List[String]] = {
  //   val remainingNumber = number.drop(digits.length)
  //   for {
  //     ending <- encode(remainingNumber)
  //     word <- words
  //   } yield List(word) ++ ending
  // }
  def encode(number: String): Set[List[String]] = {
    if (number.isEmpty) then Set(Nil)
    else {
      for {
        splitPoint <- (1 to number.length).toSet
        word <- wordsForNum(number.take(splitPoint))
        rest <- encode(number.drop(splitPoint))
      } yield word :: rest
    }
  }
}

val coder = new Coder(
  List("Scala", "scaja", "is", "fun", "dum", "sab", "lagp")
)
coder.encode("7225247386")
coder.encode("722524738")
coder.encode("123")
