package superquantstests

import org.scalatest.{FlatSpec, Matchers}
import superquants._
import longprecision._
import length._
import mass._
import render._
import shapeless.test.illTyped
import shapeless.{::, HNil}
import superquants.longprecision.time._

class TestPhysicalRulesForUnitsAndComplex extends FlatSpec with Matchers {


  type Acceleration = Complex[Long, Single[Meters] :: Negative.Squared[Seconds] :: HNil]


  "It" should "work" in {

    val seconds = 5.seconds
    val meters = 5.meters
    val kg = 5.kg

    val negativeSeconds = 5L.as[Negative.Single[Seconds]]
    val negativeMeters = 5L.as[Negative.Single[Meters]]
    val negativeKg = 5L.as[Negative.Single[Kilograms]]

    val squaredSeconds:Squared[Seconds] = seconds ** seconds

    val longValue1 = seconds ** negativeSeconds

    val secondsXmeters = seconds ** meters
    val secondsXmetersDividedLong = secondsXmeters divide 5L

    val negativeSquaredSeconds:Negative.Squared[Seconds] = negativeSeconds ** negativeSeconds
    val negativeSecondsXnegativeMeters = negativeSeconds ** negativeMeters

    val longValue2 = negativeSquaredSeconds ** squaredSeconds



    val longValue3 = secondsXmeters divide secondsXmeters



    illTyped("longValue1.pretty","value pretty is not a member of Long")
    illTyped("longValue2.pretty","value pretty is not a member of Long")
    illTyped("longValue3.pretty","value pretty is not a member of Long")
    val step = StepLong @@ 5L
    illTyped("secondsXmeters divide step","could not find implicit value for parameter r2: superquants.package.FakeTrait.+")


    println(s"seconds: ${seconds.pretty}, meters: ${meters.pretty}, kg: ${kg.pretty}")
    println(s"negativeSeconds: ${negativeSeconds.pretty}, negativeMeters: ${negativeMeters.pretty}, negativeKg: ${negativeKg.pretty}")
    println(s"longValue1: ${longValue1}")


    println(s"secondsXmeters: ${secondsXmeters.pretty}")
    println(s"secondsXmetersDividedLong: ${secondsXmetersDividedLong.pretty}")
    println(s"negativeSquaredSeconds: ${negativeSquaredSeconds.pretty}")
    println(s"negativeSecondsXnegativeMeters: ${negativeSecondsXnegativeMeters.pretty}")



    seconds.pretty shouldEqual "5 s"
    meters.pretty shouldEqual "5 m"
    kg.pretty shouldEqual "5 kg"

    negativeSeconds.pretty shouldEqual "5 / s^1"
    negativeMeters.pretty shouldEqual "5 / m^1"
    negativeKg.pretty shouldEqual "5 / kg^1"

    longValue1.toString shouldEqual "25"

    secondsXmeters.pretty shouldEqual "25 s^1 * m^1"
    secondsXmetersDividedLong.pretty shouldEqual "5 s^1 * m^1"

    negativeSquaredSeconds.pretty shouldEqual "25 / s^2"
    negativeSecondsXnegativeMeters.pretty shouldEqual "25 / s^1 * m^1"


  }

  def test[T: NoImplicit]:Unit ={}
}
