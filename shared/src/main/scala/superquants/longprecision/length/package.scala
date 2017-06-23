package superquants.longprecision

import shapeless._
import supertagged.{@@, Tag, TaggedType, OverTagged}
import superquants.MetricSystem._
import shapeless.ops.hlist.IsHCons
import superquants._
import superquants.traits.LengthTrait

package object length extends LengthTrait with LongLengthTypesTrait {

  implicit class LongLengthForRawOpsInt(val __v: Int) extends AnyVal with LengthForRawOpsTrait[Int] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }

  implicit class LongLengthForRawOpsLong(val __v: Long) extends AnyVal with LengthForRawOpsTrait[Long] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }


}

trait LongLengthTypesTrait {
  object Length extends TaggedType[Long]
  type Length[T] = (Long with Tag[Long, Length.Tag]) @@ T


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
