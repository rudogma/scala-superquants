package superquants.render

import shapeless.{::, HList, HNil}
import superquants.SuffixPrinter

trait ComplexSuffixPrinter[H <: HList, Raw] {

  def notNil: Boolean

  def apply(printNumerator: Boolean): String
}

object ComplexSuffixPrinter {

  implicit def prettySuffixForNothing[Raw]: ComplexSuffixPrinter[Nothing :: HNil, Raw] = new ComplexSuffixPrinter[Nothing :: HNil, Raw] {
    def notNil = false

    def apply(printNumerator: Boolean): String = ???
  }

  implicit def prettySuffixForNil[Raw]: ComplexSuffixPrinter[HNil, Raw] = new ComplexSuffixPrinter[HNil, Raw] {
    def notNil = false

    def apply(printNumerator: Boolean): String = ???
  }

  implicit def prettySuffixRecur[Raw, T, Tail <: HList](implicit tail: ComplexSuffixPrinter[Tail, Raw], suffix: SuffixPrinter[T], znak: ExPowZnak[T]): ComplexSuffixPrinter[T :: Tail, Raw] = new ComplexSuffixPrinter[T :: Tail, Raw] {

    def notNil = true

    def apply(printNumerator: Boolean): String = {

      if (
        (printNumerator && znak.isNumerator) ||
          (!printNumerator && !znak.isNumerator)
      ) {
        if (tail.notNil) {

          val t = tail(printNumerator).trim

          if (t.isEmpty) {
            s"${suffix()}"
          } else {
            s"${suffix()} * ${t}"
          }
        } else {
          s"${suffix()}"
        }
      } else {
        if (tail.notNil) {
          s"${tail(printNumerator)}"
        } else {
          ""
        }
      }


    }
  }
}

