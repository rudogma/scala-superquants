package superquants.longprecision

import superquants.traits.TimeTrait
import supertagged._


package object time extends TimeTrait with LongTimeTypesTrait {



  implicit class LongTimeForRawOpsInt(val __v:Int) extends AnyVal with TimeForRawOpsTrait[Int] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }

  implicit class LongTimeForRawOpsLong(val __v:Long) extends AnyVal with TimeForRawOpsTrait[Long] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }


}


trait LongTimeTypesTrait {
  object Time extends TaggedType[Long] {
    //    def zero: Seconds = Seconds(Time @@ 0L)
  }

  type Time[T] = (Long with Tag[Long, Time.Tag]) @@ T


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