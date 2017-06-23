package superquants

import shapeless.Nat

package object doubleprecision {

  type Single[T] = Pow[Double, T, PowPlus, Nat._1]
  type Squared[T] = Pow[Double, T, PowPlus, Nat._2]
  type Cubic[T] = Pow[Double, T, PowPlus, Nat._3]

  object Negative {
    type Single[T] = Pow[Double, T, PowMinus, Nat._1]
    type Squared[T] = Pow[Double, T, PowMinus, Nat._2]
    type Cubic[T] = Pow[Double, T, PowMinus, Nat._3]
  }
}
