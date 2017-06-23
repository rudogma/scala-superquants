package superquants.doubleprecision


import supertagged._
import superquants._
import MetricSystem._
import shapeless._
import superquants.traits.TimeTrait

package object time extends TimeTrait with DoubleTimeTypesTrait {



  implicit class DoubleTimeForRawOpsInt(val __v:Int) extends AnyVal with TimeForRawOpsTrait[Int] {
    protected def safecasted[T]: T = __v.toDouble.asInstanceOf[T]
  }

  implicit class DoubleTimeForRawOpsLong(val __v:Long) extends AnyVal with TimeForRawOpsTrait[Long] {
    protected def safecasted[T]: T = __v.toDouble.asInstanceOf[T]
  }

  implicit class DoubleTimeForRawOpsDouble(val __v:Double) extends AnyVal with TimeForRawOpsTrait[Double] {
    protected def safecasted[T]: T = __v.asInstanceOf[T]
  }
}


trait DoubleTimeTypesTrait {
  object Time extends TaggedType[Double] {
    def zero: Seconds = Seconds(Time @@ 0d)
  }

  type Time[T] = (Double with Tag[Double, Time.Tag]) @@ T


  object Nanoseconds extends OverTagged(Time)

  object Microseconds extends OverTagged(Time)

  object Milliseconds extends OverTagged(Time)

  val Millis = Milliseconds

  object Seconds extends OverTagged(Time)

  object Minutes extends OverTagged(Time)

  object Hours extends OverTagged(Time)

  object Days extends OverTagged(Time)

  type Nanoseconds = Nanoseconds.Type
  type Microseconds = Microseconds.Type


  type Milliseconds = Milliseconds.Type
  type Millis = Milliseconds.Type
  type Seconds = Seconds.Type
  type Minutes = Minutes.Type

  type Hours = Hours.Type
  type Days = Days.Type
}