package superquantstests

import superquants._
import longprecision._
import shapeless.{::, HList, HNil, Nat}
import time._
import length._
import mass._
import render._
import binary._
import supertagged.{@@, Tagged}
import shapeless.ops.hlist
import shapeless.ops.hlist.{Length, Prepend}
import shapeless.ops.nat.ToInt

import scala.reflect.runtime.universe.reify
import scala.language.reflectiveCalls

object TestMerge extends App {


  val seconds = 5.seconds

  val secondsF = 5L.as[Complex[Long, Single[Seconds] :: HNil]]
  val metersF = 5L.as[Complex[Long, Single[Meters] :: HNil]]

  val fff = 5L.as[Complex[Long, Single[Meters] :: Single[Seconds] :: HNil]]

  println(s"secondsF: ${secondsF.pretty}")

  val multi = secondsF *** secondsF


  val metersNegative = 5L.as[Negative.Single[Meters]]
  val secondsNegative = 5L.as[Negative.Single[Seconds]]



  val op1 = 5L.as[Complex[Long, Single[Kilograms] :: Single[Meters] :: Single[Seconds] :: HNil]]
  val op2 = 5L.as[Complex[Long, Single[Meters] :: HNil]]
  val op3 = 5L.as[Complex[Long, Single[Kilograms] :: Single[Seconds] :: HNil]]

  val op4 = 5L.as[Complex[Long, Single[Seconds] :: Single[Meters] :: HNil]]

  val op5 = 5L.as[Complex[Long, Negative.Single[Kilograms] :: Negative.Squared[Seconds] :: HNil]]
  val op6 = 5L.as[Complex[Long, Negative.Single[Kilograms] :: Negative.Single[Meters] :: HNil]]

//  op1: 5 m^1 * s^1, op2: 5 s^1
  println(s"op1: ${op1.pretty}, op2: ${op2.pretty}")


  println("---make 1 ----")
  val out2 = op1 ** op2

  println("------ make 2 -----")
  val out3 = op1 ** op3

  println("--end-make---")
//
//
////  println(s"out2: ${out2.pretty}")
////  println(s"out2: ${out2.pretty}, out3: ${out3.pretty}")
  println(s"out2: ${out2.pretty}, out3: ${out3.pretty}")

  //Single[Kilograms] :: Single[Seconds] :: HNil
  //Single[Seconds] :: Single[Meters] :: HNil
  val out4 = op3 ** op4
  println(s"out4: ${out4.pretty}")


//  val out5 = out4 make op5

  //Single[Seconds] :: Single[Meters] :: HNil
  //Single[Kilograms] :: Single[Seconds] :: HNil
  val out5 = op4 ** op3
  println(s"out5: ${out5.pretty}")
//
//
  val out6 = out5 ** op5
  val out7:Squared[Seconds] = out5 ** op6

  println(s"out6: ${out6.pretty}")
  println(s"out7: ${out7.pretty}")





  implicit class ComplexOps[Raw, Items <: HList](val __v: Raw with Complex[Raw, Items]) extends AnyVal {
    def ***[Items2 <: HList](p2:Complex[Raw, Items2]):Unit = {

    }

//    def make[NewItems <: HList, DiffOut <: HList](p2:Complex[Raw, NewItems])
//   (implicit diff:Mixer.Aux[Raw, Items, NewItems, HNil, DiffOut], cleaner:Cleaner[Raw, DiffOut])
//    :cleaner.Out = cast(__v)

  }

  def cast[T,U](value:T):U = value.asInstanceOf[U]
}
