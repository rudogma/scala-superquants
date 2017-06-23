package superquants

import shapeless.{::, HList, HNil, Nat}
import shapeless.ops.hlist.{Prepend}



trait Mixer[Raw, Items <: HList, NewItems <: HList, CollectedGlobal <: HList] {
  type Out <: HList
}


object Mixer extends MixerLow1 {

  implicit def mixerNIL[
  Raw,
  Items <: HList,
  G <: HList,
  Out <: HList
  ]
  (implicit
    prepend:Prepend.Aux[Items, G, Out]
   )
  :Aux[Raw, Items, HNil, G, Out] = dummy



  implicit def mixerPreNil[
  Raw,
  Items <: HList,
  Target, TargetZnak <: PowZnak, TargetN <: Nat,
  G <: HList,
  DiffOut <: HList
  ](implicit
    diff:Differ.Aux[Raw, Items, Pow[Raw, Target, TargetZnak, TargetN], HNil, G, HNil, DiffOut],
    prepend:Prepend.Aux[DiffOut, G, DiffOut]
   )
  :Aux[Raw, Items, Pow[Raw, Target, TargetZnak, TargetN] :: HNil, G, DiffOut] = dummy
}

trait MixerLow1 extends MixerFinal {


  implicit def mixerRecur[
  Raw,
  Items <: HList,
  Target, TargetZnak <: PowZnak, TargetN <: Nat,
  TargetTail <: HList,
  G <: HList
  ]
  (implicit
    diff:Differ[Raw, Items, Pow[Raw, Target, TargetZnak, TargetN], HNil, G, TargetTail]
  )
  :Aux[Raw, Items, Pow[Raw, Target, TargetZnak, TargetN] :: TargetTail, G, diff.Out] = dummy
}

trait MixerFinal {

  type Aux[Raw, Items <: HList, NewItems <: HList, G <: HList, Out0 <: HList] = Mixer[Raw, Items,NewItems,G]{ type Out = Out0 }

  private final val stub = new Mixer[Nothing,Nothing,Nothing,Nothing]{}
  protected def dummy[T]:T = stub.asInstanceOf[T]
}
