package superquants

import shapeless.{HList, Nat}
import shapeless.ops.hlist.Length
import shapeless.ops.nat.ToInt

/**
  * For debug. Easy-tracing implicit calls
  */
trait Len[H <: HList] {
  def apply():Int
}

object Len {
  implicit def lenImpl[H <: HList, N <: Nat](implicit len:Length.Aux[H,N], toInt:ToInt[N]):Len[H] = new Len[H] {
    def apply():Int = toInt.apply()
  }
}
