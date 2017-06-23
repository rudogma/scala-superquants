package superquants

import shapeless.Nat

package object longprecision {

  type Single[T] = Pow[Long, T, PowPlus, Nat._1]
  type Squared[T] = Pow[Long, T, PowPlus, Nat._2]
  type Cubic[T] = Pow[Long, T, PowPlus, Nat._3]

  object Negative {
    type Single[T] = Pow[Long, T, PowMinus, Nat._1]
    type Squared[T] = Pow[Long, T, PowMinus, Nat._2]
    type Cubic[T] = Pow[Long, T, PowMinus, Nat._3]
  }
}
