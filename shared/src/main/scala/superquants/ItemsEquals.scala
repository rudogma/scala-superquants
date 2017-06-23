package superquants

import shapeless.Nat


trait ItemsEquals[ItemU, Target]

object ItemsEquals {
  private val stub = new ItemsEquals[Nothing, Nothing] {}

  private def dummy[T]: T = stub.asInstanceOf[T]

  implicit def itemsEqualsImpl[U]: ItemsEquals[U, U] = dummy
  implicit def itemsEqualsPow[
  Raw, U,
  ItemZnak <: PowZnak, ItemN <: Nat,
  TargetZnak <: PowZnak, TargetN <: Nat
  ]:ItemsEquals[
    Pow[Raw, U, ItemZnak, ItemN],
    Pow[Raw, U, TargetZnak, TargetN]
    ] = dummy
}


trait ItemsNotEquals[ItemU, Target]

object ItemsNotEquals {
  private val stub = new ItemsNotEquals[Nothing, Nothing] {}
  private def dummy[T]: T = stub.asInstanceOf[T]

  implicit def itemsEqualsImpl2[U, U2]: ItemsNotEquals[U, U2] = dummy
  implicit def itemsEqualsImpl2_ambi[U]: ItemsNotEquals[U, U] = dummy


  implicit def itemsEqualsPow_ambi[
  Raw, U,
  ItemZnak <: PowZnak, ItemN <: Nat,
  TargetZnak <: PowZnak, TargetN <: Nat
  ]:ItemsNotEquals[
    Pow[Raw, U, ItemZnak, ItemN],
    Pow[Raw, U, TargetZnak, TargetN]
    ] = dummy
}

