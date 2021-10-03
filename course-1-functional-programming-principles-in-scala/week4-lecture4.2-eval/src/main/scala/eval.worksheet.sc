1 + 1

trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Var(x: String) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr

def eval(e: Expr): Int = e match {
  case Number(n)    => n
  case Sum(e1, e2)  => eval(e1) + eval(e2)
  case Prod(e1, e2) => eval(e1) * eval(e2)
}

def show(e: Expr): String = e match {
  case Number(n)   => n.toString
  case Sum(e1, e2) => s"${eval(e1)} + ${eval(e2)}"
  case Var(x)      => x
  case Prod(e1, e2) => {
    def showProd(e: Expr) = {
      e match {
        case e: Sum => s"(${show(e)})"
        case _      => eval(e)
      }
    }

    s"${showProd(e1)} * ${showProd(e2)}"
  }
}

val one = new Number(1)
val two = new Number(2)
val onePlusTwo = new Sum(one, two)
eval(onePlusTwo)
show(onePlusTwo)
val oneTimesTwo = new Prod(one, two)
eval(oneTimesTwo)
show(oneTimesTwo)
show(Prod(Sum(one, two), one))
