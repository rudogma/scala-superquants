package superquants

import shapeless.{::, HList, HNil, Nat}

trait Negate[Raw, H <: HList] extends Any {
  type Out <: HList
}

object Negate {

  type Aux[Raw, H <: HList, Out0 <: HList] = Negate[Raw, H]{ type Out = Out0 }

  private val stub = new Negate[Nothing, Nothing]{}
  private def dummy[T]:T = stub.asInstanceOf[T]


  implicit def negateNil[Raw]:Aux[Raw, HNil, HNil] = dummy


  implicit def negateRecur_MinusToPlus[Raw, U, N <: Nat, Tail <: HList](implicit tail:Negate[Raw,Tail]):Aux[Raw, Pow[Raw,U, PowMinus, N] :: Tail, Pow[Raw,U, PowPlus,N] :: tail.Out] = dummy
  implicit def negateRecur_PlusToMinus[Raw, U, N <: Nat, Tail <: HList](implicit tail:Negate[Raw,Tail]):Aux[Raw, Pow[Raw,U, PowPlus, N] :: Tail, Pow[Raw,U, PowMinus,N] :: tail.Out] = dummy
}
