package superquants

import shapeless._
import supertagged.{@@, Tagged}

import scala.annotation.implicitNotFound
import scala.language.higherKinds

package object render extends ShowerCompanions {

  trait Prettifier[H <: HList, Raw] {

    def notNil: Boolean

    def apply(v: Raw, cOrigin: UnitCompanion[_]): String
  }

  //  object Prettifier {
  implicit def prettifierForNothing[Raw]: Prettifier[Nothing :: HNil, Raw] = new Prettifier[Nothing :: HNil, Raw] {
    def notNil = false

    def apply(v: Raw, cOrigin: UnitCompanion[_]): String = ???
  }

  implicit def prettifierForNil[Raw]: Prettifier[HNil, Raw] = new Prettifier[HNil, Raw] {
    def notNil = false

    def apply(v: Raw, cOrigin: UnitCompanion[_]): String = ???
  }

  implicit def prettifierRecur[Raw, T, Tail <: HList](implicit tail: Prettifier[Tail, Raw], c: UnitCompanion[T], sc: ShowerCompanion[Raw]): Prettifier[T :: Tail, Raw] = new Prettifier[T :: Tail, Raw] {

    def notNil = true

    def apply(v: Raw, cOrigin: UnitCompanion[_]): String = {
      val (v1, v2) = sc(v, cOrigin, c)


      if (sc.lt1(v1) && tail.notNil) {
        s"${tail(v2, cOrigin)}"
      } else if (tail.notNil) {
        s"${sc.ceil(v1)} ${c.suffix} ${tail(v2, cOrigin)}"
      } else {
        s"${v1} ${c.suffix}"
      }
    }
  }

  //  }


  trait HeadInfo[H <: HList] {
    type UnitBase
    type Raw
  }

  //  object HeadInfo {
  type Aux[UnitBase0, Raw0, H <: HList] = HeadInfo[H] {type UnitBase = UnitBase0; type Raw = Raw0}

  implicit def hlistHeadInfo[Raw0, UnitBase0, U, Tail <: HList]: Aux[UnitBase0, Raw0, (Tagged[Raw0, UnitBase0] @@ U) :: Tail] = new HeadInfo[(Tagged[Raw0, UnitBase0] @@ U) :: Tail] {
    type UnitBase = UnitBase0
    type Raw = Raw0
  }

  //  }


  /**
    * Evidence that all items of hlist have the same UnitBase
    *
    * @tparam H
    * @tparam UnitBase
    */
  @implicitNotFound("Can't pretty with units from different families (UnitBase must equal) ${H} of ${UnitBase}")
  trait EvForAll[H <: HList, UnitBase] {}


  //  object EvForAll {
  implicit def evidenceForNil[Raw, UnitBase, U]: EvForAll[(Tagged[Raw, UnitBase] @@ U) :: HNil, UnitBase] = new EvForAll[(Tagged[Raw, UnitBase] @@ U) :: HNil, UnitBase] {}

  implicit def evidenceRecur[Raw, UnitBase, U, U2, Tail <: HList](implicit ev: EvForAll[(Tagged[Raw, UnitBase] @@ U2) :: Tail, UnitBase]): EvForAll[(Tagged[Raw, UnitBase] @@ U) :: (Tagged[Raw, UnitBase] @@ U2) :: Tail, UnitBase] = new EvForAll[(Tagged[Raw, UnitBase] @@ U) :: (Tagged[Raw, UnitBase] @@ U2) :: Tail, UnitBase] {}

  //  }


  trait PrettifyHelper[H <: HList, UnitBase, Raw] {

    type F[T] = Tagged[Raw, UnitBase] @@ T

    def apply[U](value: Tagged[Raw, UnitBase] @@ U)(implicit ev: EvForAll[H, UnitBase], prettifier: Prettifier[H, Raw], cOrigin: UnitCompanion[F[U]]): String
  }


  /**
    * Using: ```prettify[Kilograms :: HNil](massValue)```
    * Using: ```prettify[Kilograms :: Grams :: HNil](massValue)```
    */
  def pretty[H <: HList](implicit head: HeadInfo[H]): PrettifyHelper[H, head.UnitBase, head.Raw] = new PrettifyHelper[H, head.UnitBase, head.Raw] {

    def apply[U](value: Tagged[head.Raw, head.UnitBase] @@ U)(implicit ev: EvForAll[H, head.UnitBase], prettifier: Prettifier[H, head.Raw], cOrigin: UnitCompanion[Tagged[head.Raw, head.UnitBase] @@ U]): String = prettifier(value.asInstanceOf[head.Raw], cOrigin)
  }


  implicit class PostfixPhysicalRulesForUnits111[Raw, UnitBase, U](val __v: Tagged[Raw, UnitBase] @@ U) extends AnyVal {

    def pretty[H <: HList](implicit ev: EvForAll[H, UnitBase], prettifier: Prettifier[H, Raw], defaultPrettifier: Prettifier[(Tagged[Raw, UnitBase] @@ U) :: HNil, Raw], cOrigin: UnitCompanion[Tagged[Raw, UnitBase] @@ U]): String = {

      if (prettifier.notNil) {
        prettifier(__v, cOrigin)
      } else {
        defaultPrettifier(__v, cOrigin)
      }

    }
  }


  /** * COMPLEX ****/

  implicit class PowPrettyOps[Raw, U, Znak <: PowZnak, N <: Nat](val __v: Pow[Raw, U, Znak, N]) extends AnyVal {

    def pretty(implicit printer: SuffixPrinter[Pow[Raw, U, Znak, N]], znak: ExPowZnak[Znak]): String = {

      if (znak.isNumerator) {
        s"${__v} ${printer()}"
      } else {
        s"${__v} / ${printer()}"
      }

    }
  }

  implicit class ComplexPrettyOps[Raw, Items <: HList](val __v: Raw with Complex[Raw, Items]) extends AnyVal {

    def pretty(implicit printer: ComplexSuffixPrinter[Items, Raw]): String = {
      val numerator = printer(true)
      val denominator = printer(false)

      val numeratorString = s"${__v} ${numerator}".trim

      if (denominator.isEmpty) {
        numeratorString
      } else {
        s"${numeratorString} / ${denominator}"
      }

    }
  }

}
