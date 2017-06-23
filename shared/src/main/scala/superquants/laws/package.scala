package superquants

import scala.annotation.implicitNotFound

package object laws {

  @implicitNotFound("Not found Law to make Operation[${Op}] for operand1: ${P1}, and operand2: ${P2}")
  trait LawTrait[P1, Op <: Operation, P2] {
    type Out

    def apply(p1: P1, p2: P2): Out
  }

  type Law[P1, O <: Operation, P2, Out0] = LawTrait[P1, O, P2] {type Out = Out0}

  def Law[P1, O <: Operation, P2, Out0](f: (P1, P2) => Any) = new LawTrait[P1, O, P2] {
    type Out = Out0

    def apply(p1: P1, p2: P2): Out0 = f(p1, p2).asInstanceOf[Out0]
  }

  implicit class LawOps[T](val __p1:T) extends AnyVal {
    def multiply[U](p2:U)(implicit law:LawTrait[T, Multiply,U]):law.Out = law(__p1, p2)
    def divide[U](p2:U)(implicit law:LawTrait[T, Divide, U]):law.Out = law(__p1, p2)
    def minus[U](p2:U)(implicit law:LawTrait[T, Minus, U]):law.Out = law(__p1, p2)
    def plus[U](p2:U)(implicit law:LawTrait[T, Plus, U]):law.Out = law(__p1, p2)
  }
}
