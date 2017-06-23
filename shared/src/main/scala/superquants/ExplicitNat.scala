package superquants

import shapeless.Nat

trait ExplicitNat[T <: Nat]{
  type Out = T
}

object ExplicitNat {
  type Aux[T <: Nat] = ExplicitNat[T]

  private val stub = new ExplicitNat[Nothing]{}
  private def dummy[T]:T = stub.asInstanceOf[T]

  implicit val explicitNat0:Aux[Nat._0] = dummy
  implicit val explicitNat1:Aux[Nat._1] = dummy
  implicit val explicitNat2:Aux[Nat._2] = dummy
  implicit val explicitNat3:Aux[Nat._3] = dummy
  implicit val explicitNat4:Aux[Nat._4] = dummy
  implicit val explicitNat5:Aux[Nat._5] = dummy
  implicit val explicitNat6:Aux[Nat._6] = dummy
  implicit val explicitNat7:Aux[Nat._7] = dummy
  implicit val explicitNat8:Aux[Nat._8] = dummy
  implicit val explicitNat9:Aux[Nat._9] = dummy
  implicit val explicitNat10:Aux[Nat._10] = dummy
  implicit val explicitNat11:Aux[Nat._11] = dummy
  implicit val explicitNat12:Aux[Nat._12] = dummy
  implicit val explicitNat13:Aux[Nat._13] = dummy
  implicit val explicitNat14:Aux[Nat._14] = dummy
  implicit val explicitNat15:Aux[Nat._15] = dummy
  implicit val explicitNat16:Aux[Nat._16] = dummy
  implicit val explicitNat17:Aux[Nat._16] = dummy
  implicit val explicitNat18:Aux[Nat._16] = dummy
  implicit val explicitNat19:Aux[Nat._16] = dummy
  implicit val explicitNat20:Aux[Nat._16] = dummy
  implicit val explicitNat21:Aux[Nat._16] = dummy
  implicit val explicitNat22:Aux[Nat._16] = dummy
}