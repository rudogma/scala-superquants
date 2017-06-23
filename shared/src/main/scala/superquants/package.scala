import shapeless.Nat
import shapeless.ops.nat.ToInt
import supertagged._
import shapeless._
import shapeless.ops.hlist.{IsHCons, Last}
import superquants.utils.SuperDummyImplicit

import scala.language.higherKinds
import scala.annotation.implicitNotFound


package object superquants
  extends Operations
    with RawCompanions
    with PhysicalRulesOps {


  private def cast[T, U](v: U): T = v.asInstanceOf[T]




  //** CONVERTING

  trait Converter[P1, P2] {
    def apply(p1: P1): P2
  }

  trait PreConverter[From, To] {
    def apply(f: From => To): Converter[From, To]
  }

  object Converter {
    def apply[From, To]: PreConverter[From, To] = new PreConverter[From, To] {
      def apply(f: From => To): Converter[From, To] = new Converter[From, To] {
        def apply(p1: From): To = f(p1)
      }
    }
  }


  implicit class ConverterLawOps2[T](val op1: T) extends AnyVal {

    def in[T2](target: TaggedType[T2])(implicit law: Converter[T, target.Type]): target.Type = law(op1)
  }



  /**** UNITS BLOCK ***/

  /**
    * Чтобы можно было использовать динамические значения ratio (например для обмена валюты они могут браться из бд или сторонних сервисов и обновляться время от времени в течение жизни программы)
    */
  trait UnitCompanion[T] {
    def ratio:Double
    def name:String
    def suffix:String
  }

  object UnitCompanion {
    def apply[T](ratio0:Double, name0:String, suffix0:String):UnitCompanion[T] = new UnitCompanion[T] {
      def ratio = ratio0
      def name = name0
      def suffix = suffix0
    }
  }

  trait UnitConverter[T] {
    type UC = UnitCompanion[_]
    def apply(value:T, c1:UC, c2:UC):T
  }

  implicit val unitConverterLong:UnitConverter[Long] = new UnitConverter[Long]{ def apply(value:Long, c1:UC, c2:UC) = (value * c1.ratio / c2.ratio).toLong }
  implicit val unitConverterDouble:UnitConverter[Double] = new UnitConverter[Double]{ def apply(value:Double, c1:UC, c2:UC) = (value * c1.ratio / c2.ratio) }


  implicit final class ConvertationOpsForUnits[Raw, UnitBase, U](val __v:Tagged[Raw,UnitBase] @@ U) extends AnyVal {
    type FF[T] = Tagged[Raw, UnitBase] @@ T


    @inline final def in[R0, R1 <: TaggedType[R0]](target: OverTagged[R0, R1])(implicit converter: UnitConverter[Raw], c1: UnitCompanion[FF[U]], c2: UnitCompanion[FF[target.Tag]]): Tagged[Raw, UnitBase] @@ target.Tag = converter(__v, c1, c2).asInstanceOf[FF[target.Tag]]
  }




  /***************/


  trait SuffixPrinter[T] {
    def apply(): String
  }

  implicit def suffixForUnit[Raw, UnitBase, U](implicit c: UnitCompanion[Tagged[Raw, UnitBase] @@ U]): SuffixPrinter[Tagged[Raw, UnitBase] @@ U] =
    new SuffixPrinter[Tagged[Raw, UnitBase] @@ U] { def apply():String = c.suffix }





  implicit class PostfixPhysicalRulesForUnits[Raw, UnitBase, U](val __v: Tagged[Raw, UnitBase] @@ U) extends AnyVal with PhysicalRulesForUnits[Raw,UnitBase,U] {

  }


  implicit class PhysicalRulesForTaggedTypes[Raw, U](val __v: Raw @@ U) extends AnyVal with PhysicalRules[Raw,U] {

  }



//  implicit class PowPrettyOps[T, N <: Nat](val __v: Pow[Long, T, N]) extends AnyVal {
//    def pretty(implicit c: UnitCompanion[T], toInt: ToInt[N]): String = s"${__v} ${c.suffix}^${toInt.apply()}"
//  }


  /**
    * For debug purposes
    */
  @implicitNotFound("NoImplicit for: ${T}")
  trait NoImplicit[T]

  /**
    * Don't know why, but without these block here Intellij Idea fails to compile. (Sbt compiles ok). Magic
    */
  trait NoNothing[T]
  implicit def anyNo2:NoNothing[Nothing] = ???



  @implicitNotFound("Wrong RAW type `${Raw}` or no evidence for that complex-case `${T}`. Evidence failed. (May be you forgot using :: HNil if cast complex)")
  trait AsEvidence[T, Raw]

  object AsEvidence {
    private val stub = new AsEvidence[Nothing,Nothing] {}
    private def dummy[T <: AsEvidence[_,_]]:T = stub.asInstanceOf[T]


    implicit def asEvidenceForUnits[Raw, Raw0 <: Raw,UnitBase,U]:AsEvidence[Tagged[Raw0,UnitBase] @@ U, Raw] = dummy
    implicit def asEvidenceForPow[Raw, Raw0 <: Raw, Unit, Znak <: PowZnak, N <: Nat]:AsEvidence[Pow[Raw0, Unit, Znak, N],Raw] = dummy
    implicit def asEvidenceForComplex[Raw, Raw0 <: Raw, H <: HList]:AsEvidence[Complex[Raw0, H],Raw] = dummy

  }




  implicit class SuperquantsRawOps[Raw](val __v:Raw) extends AnyVal {

    def as[T](implicit ev:AsEvidence[T, Raw]):T = __v.asInstanceOf[T]
  }


  //***---FRACT

  trait PowT[U, Znak <: PowZnak, N0 <: Nat] extends Any {
    type Level = N0
  }

  type Pow[Raw, Unit, Znak <: PowZnak, N <: Nat] = Raw with PowT[Unit, Znak, N]


  trait ComplexT[Raw0, Items <: HList] extends Any {
    type Raw = Raw0
  }


  type Complex[Raw0, Items <: HList] = Raw0 with ComplexT[Raw0, Items] {
    type Raw = Raw0
  }

  implicit def suffixForNewPow[Raw, UnitBase, U, Znak <: PowZnak, N <: Nat](implicit c: UnitCompanion[Tagged[Raw, UnitBase] @@ U], toInt: ToInt[N]): SuffixPrinter[Pow[Raw, Tagged[Raw, UnitBase] @@ U, Znak, N]] = new SuffixPrinter[Pow[Raw, Tagged[Raw, UnitBase] @@ U, Znak, N]]{
    def apply():String = {
      if (toInt.apply() > 0) {
        s"${c.suffix}^${toInt.apply()}"
      } else {
        s"${c.suffix}"
      }
    }
  }


  implicit class PostfixOpsForComplex[Raw, Items <: HList](val __v: Raw with ComplexT[Raw, Items]) extends AnyVal with PhysicalRulesForComplexTrait[Raw,Items] {
  }

  implicit class PostfixOpsForPowLong[U, Znak <: PowZnak, N <: Nat](val __v: Pow[Long, U, Znak, N]) extends AnyVal with PhysicalRulesForPowTrait[Long,U,Znak,N]{

  }
  implicit class PostfixOpsForPowDouble[U, Znak <: PowZnak, N <: Nat](val __v: Pow[Double, U, Znak, N]) extends AnyVal with PhysicalRulesForPowTrait[Double,U,Znak,N]{

  }


  private sealed trait FakeTrait[T]

}