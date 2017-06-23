package superquants

import shapeless.ops.hlist.{Length, Prepend}
import shapeless.ops.nat.ToInt
import shapeless.{::, HList, HNil, Nat}


trait Differ[Raw, Items <: HList, Item, CollectedLocal <: HList, CollectedGlobal <: HList, NextTail <: HList] {
  type Out <: HList
}

object Differ extends DifferLowFinal {

  implicit def target_not_found[
  Raw,
  Target, TargetZnak <: PowZnak, TargetN <: Nat,
  CollectedLocal <: HList, G <: HList, NextTail <: HList,
  GNew <: HList
  ]
  (implicit
   prependG: Prepend.Aux[Pow[Raw, Target, TargetZnak, TargetN] :: HNil, G, GNew],
   mixer:Mixer[Raw, CollectedLocal, NextTail, GNew]
  )
  :Aux[
    Raw,
    HNil,
    Pow[Raw, Target, TargetZnak, TargetN],
    CollectedLocal, G, NextTail,
    mixer.Out
    ] = dummy



  implicit def target_found[
  Raw,
  Item, ItemZnak <: PowZnak, ItemN <: Nat,
  Target, TargetZnak <: PowZnak, TargetN <: Nat,
  Tail <: HList,
  CollectedLocal <: HList, G <: HList, NextTail <: HList,

  SumZnak <: PowZnak, SumN <: Nat, SumList <: HList,
  GNew <: HList,LocalNew <: HList
  ]
  (implicit
   evi:ItemsEquals[Item, Target],
   sum:PowSum.Aux[Item, HNil, ItemZnak, ItemN, TargetZnak, TargetN, SumZnak, SumN, SumList],
   prependG:Prepend.Aux[SumList, G, GNew],
   prependLocal:Prepend.Aux[CollectedLocal, Tail, LocalNew],
   mixer:Mixer[Raw, LocalNew, NextTail, GNew]
  )
  :Aux[
    Raw,
    Pow[Raw, Item, ItemZnak, ItemN] :: Tail,
    Pow[Raw, Target, TargetZnak, TargetN],
    CollectedLocal, G, NextTail,
    mixer.Out
    ] = dummy
}

trait DifferLowFinal {
  type Aux[
    Raw,
    Items <: HList,
    Item,
    CollectedLocal <: HList, G <: HList,
    NextTail <: HList,
    Out0
  ] = Differ[Raw, Items, Item, CollectedLocal, G, NextTail]{ type Out = Out0}


  private val stub = new Differ[Nothing,Nothing,Nothing,Nothing,Nothing,Nothing]{}
  protected def dummy[T]:T = stub.asInstanceOf[T]



  implicit def diff_recur[
  Raw,
  Item, ItemZnak <: PowZnak, ItemN <: Nat,
  Target, TargetZnak <: PowZnak, TargetN <: Nat,
  Tail <: HList,
  CollectedLocal <: HList, G <: HList, NextTail <: HList,
  PrependOut <: HList
  ]
  (implicit
   evi:ItemsNotEquals[Item, Target],
   prepend:Prepend.Aux[CollectedLocal, Pow[Raw, Item, ItemZnak, ItemN] :: HNil, PrependOut],
   tail:Differ[Raw, Tail, Pow[Raw, Target, TargetZnak, TargetN], PrependOut, G, NextTail]
  )
  :Aux[
    Raw,
    Pow[Raw, Item, ItemZnak, ItemN] :: Tail,
    Pow[Raw, Target, TargetZnak, TargetN],
    CollectedLocal, G, NextTail,
    tail.Out
    ] = dummy
}


