package superquants


//import shapeless.{HList, Nat, Succ, ::, HNil}
//
//trait TopSum[Item1, Item2, Tail <: HList] {
//  type OutZnak <: PowZnak
//  type Out <: Nat
//  type OutList <: HList
//}
//object TopSum extends TopSumLowPriority {
//
//  implicit def tsum0[Powed, Tail <: HList, Left <: Nat](implicit minat:ExplicitNat[Left])
//  :Aux[Powed, Tail, PowPlus, Left, PowPlus, Nat._0, PowPlus, minat.Out, Pow[Long, Powed, PowPlus, minat.Out] :: Tail] = tsumDummy
//
//  implicit def tsum1[Powed, Tail <: HList, Left <: Nat, Right <: Nat]
//  (implicit next:TopSum[Powed, Tail, PowPlus, Succ[Left], PowPlus, Right])
//  :Aux[Powed, Tail, PowPlus, Left, PowPlus, Succ[Right], PowPlus, next.Out, Pow[Long, Powed, PowPlus, next.Out] :: Tail] = tsumDummy
//
//
//
//
//  implicit def tsumMinusMinus0[Powed, Tail <: HList, Left <: Nat](implicit minat:ExplicitNat[Left])
//  :Aux[Powed, Tail, PowMinus, Left, PowMinus, Nat._0, PowMinus, minat.Out, Pow[Long, Powed, PowMinus, minat.Out] :: Tail] = tsumDummy
//
//  implicit def tsumMinusMinusRecur[Powed, Tail <: HList, Left <: Nat, Right <: Nat]
//  (implicit next:TopSum[Powed, Tail, PowMinus, Succ[Left], PowMinus, Right])
//  :Aux[Powed, Tail, PowMinus, Left, PowMinus, Succ[Right], PowMinus, next.Out, Pow[Long, Powed, PowMinus, next.Out] :: Tail] = tsumDummy
//
//
//
//
//
//
//  implicit def tsumPlusMinus000[Powed, Tail <: HList]
//  :Aux[Powed, Tail, PowPlus, Nat._0, PowMinus, Nat._0, PowPlus, Nat._0, Pow[Long, Powed, PowPlus, Nat._0] :: Tail] = tsumDummy
//
//  implicit def tsumPlusMinus00[Powed, Tail <: HList, Left <: Nat](implicit minat:ExplicitNat[Left])
//  :Aux[Powed, Tail, PowPlus, Left, PowMinus, Nat._0, PowPlus, minat.Out, Pow[Long, Powed, PowPlus, minat.Out] :: Tail] = tsumDummy
//
//  implicit def tsumPlusMinus01[Powed, Tail <: HList, Right <: Nat](implicit minat:ExplicitNat[Right])
//  :Aux[Powed, Tail, PowPlus, Nat._0, PowMinus, Right, PowMinus, minat.Out, Pow[Long, Powed, PowMinus, minat.Out] :: Tail] = tsumDummy
//
//  //recur in LowPriority
//
//  implicit def tsumPlusMinusSame[Powed, Tail <: HList, N <: Nat]
//  :Aux[Powed, Tail, PowPlus, N, PowMinus, N, PowPlus, Nat._0, Tail] = tsumDummy
//
//
//
//
//  implicit def tsumMinusPlus000[Powed, Tail <: HList]
//  :Aux[Powed, Tail, PowMinus, Nat._0, PowPlus, Nat._0, PowMinus, Nat._0, Pow[Long, Powed, PowMinus, Nat._0] :: Tail] = tsumDummy
//
//  implicit def tsumMinusPlus00[Powed, Tail <: HList, Left <: Nat](implicit minat:ExplicitNat[Left])
//  :Aux[Powed, Tail, PowMinus, Left, PowPlus, Nat._0, PowMinus, minat.Out, Pow[Long, Powed, PowMinus, minat.Out] :: Tail] = tsumDummy
//
//  implicit def tsumMinusPlus01[Powed, Tail <: HList, Right <: Nat](implicit minat:ExplicitNat[Right])
//  :Aux[Powed, Tail, PowMinus, Nat._0, PowPlus, Right, PowPlus, minat.Out, Pow[Long, Powed, PowPlus, minat.Out] :: Tail] = tsumDummy
//
//  //recur in LowPriority
//
//  implicit def tsumMinusPlusSame[Powed, Tail <: HList, N <: Nat]
//  :Aux[Powed, Tail, PowMinus, N, PowPlus, N, PowMinus, Nat._0, Tail] = tsumDummy
//
//}
//
//
//trait TopSumLowPriority {
//  type Aux[Item1, Item2, Tail <: HList, OutZnak0 <: PowZnak, Out0 <: Nat, OutList0 <: HList] =
//    TopSum[Item1, Item2, Tail]{ type OutZnak = OutZnak0 ; type Out = Out0; type OutList = OutList0 }
//
//  val tsumStub = new TopSum[Nothing,Nothing,Nothing]{}
//  def tsumDummy[T]:T = tsumStub.asInstanceOf[T]
//
//
//
//  implicit def tsumPlusMinusRecur[Powed, Tail <: HList, Left <: Nat, Right <: Nat]
//  (implicit next:TopSum[Powed, Tail, PowPlus, Left, PowMinus, Right])
//  :Aux[Powed, Tail, PowPlus, Succ[Left], PowMinus, Succ[Right], next.OutZnak, next.Out, Pow[Long, Powed, next.OutZnak, next.Out] :: Tail] = tsumDummy
//
//
//  implicit def tsumMinusPlusRecur[Powed, Tail <: HList, Left <: Nat, Right <: Nat]
//  (implicit next:TopSum[Powed, Tail, PowMinus, Left, PowPlus, Right])
//  :Aux[Powed, Tail, PowMinus, Succ[Left], PowPlus, Succ[Right], next.OutZnak, next.Out, Pow[Long, Powed, next.OutZnak, next.Out] :: Tail] = tsumDummy
//
//
//}


