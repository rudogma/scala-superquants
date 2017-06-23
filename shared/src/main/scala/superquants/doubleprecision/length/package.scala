package superquants.doubleprecision


import shapeless._
import superquants._
import supertagged._
import superquants.traits.LengthTrait


package object length extends LengthTrait with DoubleLengthTypesTrait {


  implicit class DoubleLengthForRawOpsInt(val __v: Int) extends AnyVal with LengthForRawOpsTrait[Int] {
    protected def safecasted[T]: T = __v.toDouble.asInstanceOf[T]
  }

  implicit class DoubleLengthForRawOpsLong(val __v: Long) extends AnyVal with LengthForRawOpsTrait[Long] {
    protected def safecasted[T]: T = __v.toDouble.asInstanceOf[T]
  }

  implicit class DoubleLengthForRawOpsDouble(val __v: Double) extends AnyVal with LengthForRawOpsTrait[Double] {
    protected def safecasted[T]: T = __v.asInstanceOf[T]
  }


}

trait DoubleLengthTypesTrait {

  object Length extends TaggedType[Double]
  type Length[T] = (Double with Tag[Double, Length.Tag]) @@ T


  object Nanometers extends OverTagged(Length)
  type Nanometers = Nanometers.Type


  object Millimeters extends OverTagged(Length)
  type Millimeters = Millimeters.Type

  object Centimeters extends OverTagged(Length)
  type Centimeters = Centimeters.Type

  object Decimeters extends OverTagged(Length)
  type Decimeters = Decimeters.Type

  object Meters extends OverTagged(Length)
  type Meters = Meters.Type

  object Kilometers extends OverTagged(Length)
  type Kilometers = Kilometers.Type


  object InternationalMiles extends OverTagged(Length)
  type InternationalMiles = InternationalMiles.Type

}