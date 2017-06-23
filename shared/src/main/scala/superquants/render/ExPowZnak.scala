package superquants.render

import shapeless.Nat
import superquants.{Pow, PowMinus, PowPlus}

trait ExPowZnak[T] {
  def isNumerator: Boolean
}

object ExPowZnak {

  private val stubTrue = new ExPowZnak[Nothing]{
    def isNumerator = true
  }

  private val stubFalse = new ExPowZnak[Nothing]{
    def isNumerator = false
  }

  private def dummyTrue[T]:T = stubTrue.asInstanceOf[T]
  private def dummyFalse[T]:T = stubFalse.asInstanceOf[T]


  implicit def exPowZnakPlus[Raw, Unit, N <: Nat]: ExPowZnak[Pow[Raw, Unit, PowPlus, N]] = dummyTrue

  implicit def exPowZnakMinus[Raw, Unit, N <: Nat]: ExPowZnak[Pow[Raw, Unit, PowMinus, N]] = dummyFalse

  implicit def exPowZnakPlus0:ExPowZnak[PowPlus] = dummyTrue
  implicit def exPowZnakMinus0:ExPowZnak[PowMinus] = dummyFalse
}


