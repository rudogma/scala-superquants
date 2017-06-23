package superquantstests

import org.scalatest.FlatSpec
import supertagged._
import shapeless.{HNil, Nat, ::}
import shapeless.test.illTyped
import superquants._
import superquants.longprecision.time._
import superquants.longprecision.length._

import scala.annotation.implicitNotFound

class TestCast extends FlatSpec {

  type Single[T] = Pow[Long, T, PowPlus, Nat._1]
  type Squared[T] = Pow[Long, T, PowPlus, Nat._2]

  type Acceleration = Complex[Long, Single[Meters] :: Squared[Seconds] :: HNil]

  "Casting" should "work" in {


    val sec = 5L.as[Seconds]
    val sec2:Seconds = 5L.as[Seconds]
    illTyped("5.as[Seconds]","Wrong RAW type.+")


    val acc = 5L.as[Acceleration]
    val acc2:Acceleration = 5L.as[Acceleration]
    illTyped("5.as[Acceleration]","Wrong RAW type.+")


  }

}
