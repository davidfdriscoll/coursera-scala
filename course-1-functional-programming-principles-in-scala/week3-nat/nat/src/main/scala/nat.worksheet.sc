/*

This worksheet implements an exercise on natural numbers (integers > 0), showing
how it is possible to implement these numbers without use of primitive types.
Odersky implemented a boolean in the lecture, which this exercise takes as a given.
This implementation implements numbers as a linked list.
 */

import scala.collection.immutable
abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat
  def +(that: Nat): Nat
  def -(that: Nat): Nat
  def toInt: Int
}

object Zero extends Nat {
  def isZero: Boolean = true
  def predecessor: Nat = Zero
  def successor: Nat = Succ(Zero)
  def +(that: Nat): Nat = that
  def -(that: Nat): Nat = Zero
  def toInt: Int = 0
}

case class Succ(val n: Nat) extends Nat {
  def isZero: Boolean = false
  def predecessor: Nat = n
  def successor: Nat = Succ(this)
  def +(that: Nat): Nat = {
    def loop(countUp: Nat, countDown: Nat): Nat = {
      if (countDown == Zero) countUp
      else loop(countUp.successor, countDown.predecessor)
    }
    loop(this, that)
  }
  def -(that: Nat): Nat = {
    def loop(o1: Nat, o2: Nat): Nat = {
      if (o2 == Zero) o1
      else loop(o1.predecessor, o2.predecessor)
    }
    loop(this, that)
  }
  def toInt(): Int = {
    def loop(n: Nat, count: Int): Int = {
      if (n == Zero) count
      else loop(n.predecessor, count + 1)
    }
    loop(this, 0)
  }
}

1 + 1
Zero.isZero
val one = new Succ(Zero)
val two = new Succ(one)
Zero.successor
one.toInt
two.toInt
val four = two + two
val eight = four + four
val six = eight - two
val seven = eight - one
seven.toInt
eight.toInt
(six - eight).toInt
(six - six).toInt
