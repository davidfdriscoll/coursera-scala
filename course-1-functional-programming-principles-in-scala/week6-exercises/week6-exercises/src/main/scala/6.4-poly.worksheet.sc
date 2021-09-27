class Polynom(nonZeroTerms: Map[Int, Double]) {
  val terms = nonZeroTerms.withDefaultValue(0.0)

  def +(other: Polynom): Polynom = {
    Polynom(other.terms.foldRight(terms)(addTerms))
    // Polynom(terms ++ other.terms.map((exp, coeff) => (exp, terms(exp) + coeff)))
  }

  def addTerms(ec: (Int, Double), acc: Map[Int, Double]): Map[Int, Double] = {
    val (exp, coeff) = ec
    acc + (exp -> (coeff + acc(exp)))
    // acc.get(exp) match {
    //   case Some(current) => acc + (exp -> (coeff + current))
    //   case None          => acc + (exp -> coeff)
    // }
  }

  override def toString = {
    def handleExp(exp: Int) = exp match {
      case 0 => ""
      case 1 => "x"
      case _ => s"x^${exp}"
    }

    def addTerm(acc: String, ec: (Int, Double)): String = {
      val (exp, coeff) = ec
      acc match {
        case "0" => s"${coeff}${handleExp(exp)}"
        case _ => {
          if (coeff > 0) acc + s" + ${coeff}${handleExp(exp)}"
          else acc + s" - ${-coeff}${handleExp(exp)}"
        }
      }
    }

    terms.toSeq
      .sortWith((a, b) => a._1 > b._1)
      .foldLeft("0")(addTerm)
  }
}

// x2 + 1
val poly1 = new Polynom(Map(2 -> 1.0, 1 -> 2.0, 0 -> 1.0))
// x3 - 2
val poly2 = new Polynom(Map(3 -> 1.0, 0 -> -2.0))
val poly3 = new Polynom(Map())

poly1 + poly2
