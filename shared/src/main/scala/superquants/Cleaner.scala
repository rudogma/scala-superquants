package superquants


import shapeless.{::, HList, HNil, Nat}

trait Cleaner[Raw, H <: HList] {
  type Out
}

object Cleaner extends CleanerLowPriority {

  implicit def cleanerRaw[Raw]:Aux[Raw, HNil, Raw] = dummy
  implicit def cleanerSinglePow[Raw,U]:Aux[Raw, Pow[Raw, U, PowPlus, Nat._1] :: HNil, U] = dummy
}

trait CleanerLowPriority extends CleanerLowPriorityFinal {

  implicit def cleanerOneItem[Raw, U, Znak <: PowZnak, N <: Nat]:Aux[Raw, Pow[Raw, U, Znak, N] :: HNil, Pow[Raw,U,Znak,N]] = dummy
}

trait CleanerLowPriorityFinal {

  type Aux[Raw, H <: HList, Out0] = Cleaner[Raw, H]{ type Out = Out0 }

  protected val stub = new Cleaner[Nothing,Nothing]{}
  protected def dummy[T]:T = stub.asInstanceOf[T]

  implicit def cleanerDefault[Raw, H <: HList]:Aux[Raw, H, Complex[Raw,H]] = dummy
}
