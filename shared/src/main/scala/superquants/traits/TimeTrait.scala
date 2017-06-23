package superquants.traits

import shapeless.{::, HList, HNil}
import supertagged._
import superquants._
import superquants.MetricSystem.{Milli, Nano, Uno}
import superquants._

trait TimeTrait {

  type Nanoseconds
  type Microseconds
  type Milliseconds
  type Millis
  type Seconds
  type Minutes
  type Hours
  type Days


  implicit val cNano = UnitCompanion[Nanoseconds](Nano, "Nanosecond", "ns")
  implicit val cMilli = UnitCompanion[Milliseconds](Milli, "Millisecond", "ms")
  implicit val cSecond = UnitCompanion[Seconds](Uno, "Second", "s")
  implicit val cMinute = UnitCompanion[Minutes](60d, "Minute", "m")
  implicit val cHours = UnitCompanion[Hours](60 * 60d, "Hour", "h")
  implicit val cDays = UnitCompanion[Days](60 * 60 * 24d, "Day", "d")


  trait TimeForRawOpsTrait[Raw] extends Any {

    protected def safecasted[T]: T

    def day:Days = safecasted
    def days:Days = safecasted

    def hours:Hours = safecasted

    def minutes:Minutes = safecasted

    def s:Seconds = safecasted
    def seconds:Seconds = safecasted

    def ms:Milliseconds = safecasted
    def millis:Milliseconds = safecasted
    def milliseconds:Milliseconds = safecasted
    def nanos:Nanoseconds = safecasted
  }


}
