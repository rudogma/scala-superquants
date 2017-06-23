package superquantstests

import org.scalatest.{FlatSpec, Matchers}
import superquants._
import longprecision._
import longprecision.time._
import longprecision.length._
import render._
import shapeless.test.illTyped
import shapeless.{::, HNil, Nat}


class TestPowAndComplex extends FlatSpec with Matchers {

  type FractMeters = Complex[Long, Single[Meters] :: HNil]
  type FractSeconds = Complex[Long, Single[Seconds] :: HNil]


  "It" should "work" in {

    val meters = 100.meters
    val seconds = 5.seconds

    //Squared[Meters]]
    val squaredMeters = 25L.as[Pow[Long, Meters, PowPlus, Nat._2]]

    //Squared[Seconds]]
    val squaredSeconds = 25L.as[Pow[Long, Seconds, PowPlus, Nat._2]]




    val metersF: FractMeters = 5L.as[FractMeters]
    val secondsF: FractSeconds = 5L.as[FractSeconds]

    val singleSeconds = 5L.as[Single[Seconds]]


    val fractSquaredMeters = metersF ** meters
    val fractSquaredSeconds = secondsF ** 5.seconds

    val metersXseconds0 = metersF ** seconds


    val metersXseconds = meters ** seconds


    val squaredMetersXseconds = metersXseconds ** 5.meters
    val metersXsquaredSeconds = metersXseconds ** 5.seconds

//    println(metersXseconds.pretty)
////    println(metersXsquaredSeconds.pretty)


    val fractSquaredMeters2 = squaredMetersXseconds divide 5.seconds

    val squaredSecondsDIVmeters = metersXsquaredSeconds divide squaredMeters



    val metersXsquaredSeconds2 = metersXseconds ** singleSeconds
    val metersXcubicSeconds = metersXseconds ** squaredSeconds
    val metersPerSquaredSeconds = meters divide squaredSeconds
    val metersAgain = metersPerSquaredSeconds ** squaredSeconds

    onlyMeters(metersAgain)


    println(s"metersF: ${metersF.pretty}")
    println(s"secondsF: ${secondsF.pretty}")
    println(s"squaredMeters: ${squaredMeters.pretty}")
    println(s"squaredSeconds: ${squaredSeconds.pretty}")


    println(s"metersAgain: ${metersAgain.pretty}")

    println(s"fractSquaredMeters: ${fractSquaredMeters.pretty}")
    println(s"fractSquaredSeconds: ${fractSquaredSeconds.pretty}")
    println(s"metersXseconds: ${metersXseconds.pretty}")
    println(s"squaredMetersXseconds: ${squaredMetersXseconds.pretty}")
    println(s"metersXsquaredSeconds: ${metersXsquaredSeconds.pretty}")

    println(s"metersXsquaredSeconds2: ${metersXsquaredSeconds2.pretty}")
    println(s"metersXcubicSeconds: ${metersXcubicSeconds.pretty}")
    println(s"metersPerSquaredSeconds: ${metersPerSquaredSeconds.pretty}")

    println(s"fractSquaredMeters2: ${fractSquaredMeters2.pretty}")
    println(s"squaredSecondsDIVmeters: ${squaredSecondsDIVmeters.pretty}")


    metersF.pretty shouldEqual "5 m^1"
    secondsF.pretty shouldEqual "5 s^1"
    squaredMeters.pretty shouldEqual "25 m^2"
    squaredSeconds.pretty shouldEqual "25 s^2"

    metersAgain.pretty shouldEqual "100 m"

    fractSquaredMeters.pretty shouldEqual "500 m^2"
    fractSquaredSeconds.pretty shouldEqual "25 s^2"
    metersXseconds.pretty shouldEqual "500 m^1 * s^1"
    squaredMetersXseconds.pretty shouldEqual "2500 s^1 * m^2"
    metersXsquaredSeconds.pretty shouldEqual "2500 m^1 * s^2"

    metersXsquaredSeconds2.pretty shouldEqual "2500 m^1 * s^2"
    metersXcubicSeconds.pretty shouldEqual "12500 m^1 * s^3"
    metersPerSquaredSeconds.pretty shouldEqual "4 m^1 / s^2"

    fractSquaredMeters2.pretty shouldEqual "12500 m^2"
    squaredSecondsDIVmeters.pretty shouldEqual "100 s^2 / m^1"



    //    metersF: 5 m^1
    //    secondsF: 5 s^1
    //    fractSquaredMeters: 500 m^2
    //    fractSquaredSeconds: 25 s^2
    //    metersXseconds: 5 m^1 * s^1
    //    squaredMetersXseconds: 25 m^2 * s^1
    //    metersXsquaredSeconds: 25 m^1 * s^2
    //    fractSquaredMeters2: 5 m^2
    //    squaredSecondsDIVmeters: 1 s^2 / m^1
  }

  def test(v:DummyImplicit){}
  def onlyMeters(v:Meters):Unit = {}
}
