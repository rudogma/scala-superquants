package superquantstests

import shapeless.{::, HNil, Nat}
import spire.syntax.cfor
import spire.syntax.cfor
import superquants._
import superquants.longprecision._
import length._
import shapeless.test.illTyped
import time._


/**
  * Long based bench is slower then based on Double
  */

//  Starting limit = 10kk
//    [run#0] ended in 1327ms
//    [run#1] ended in 841ms
//    [run#2] ended in 799ms
//    [run#3] ended in 833ms
//    [run#4] ended in 824ms
//    [run#5] ended in 822ms
//  [bench][Squants][runs=5] ended in 6199ms
//    total: 10831708375000
//  [run#0] ended in 73ms
//    [run#1] ended in 60ms
//    [run#2] ended in 56ms
//    [run#3] ended in 68ms
//    [run#4] ended in 56ms
//  [bench][Raw Long][runs=5] ended in 313ms
//    total: 10831708375000

//  Starting. Limit=50 000 000
//    [run#0] ended in 4362ms
//    [run#1] ended in 3575ms
//    [run#2] ended in 3508ms
//    [run#3] ended in 3521ms
//    [run#4] ended in 3502ms
//  [bench][Squants][runs=5] ended in 18468ms
//    total: 1354126041875000
//  [run#0] ended in 293ms
//    [run#1] ended in 292ms
//    [run#2] ended in 281ms
//    [run#3] ended in 286ms
//    [run#4] ended in 289ms
//  [bench][Raw Long][runs=5] ended in 1441ms
//    total: 1354126041875000

object Bench extends App {


  var limit = 1000 * 1000 * 50 //0

  Thread.sleep(1.seconds in Millis)

  println(s"Starting. Limit=${limit}")


  type Acceleration = Complex[Long, Single[Meters] :: Negative.Squared[Seconds] :: HNil]

  @inline def calculate_distance_1(time: Seconds, acc: Acceleration): Meters = {
    val squaredTime = time ** time
    val r0:Meters = acc ** squaredTime
    val rr = r0 divide 2L

    val step = StepLong @@ 5L
    illTyped("acc ** squaredTime divide step","could not find implicit value for parameter d: superquants.package.FakeTrait.+")

    rr
  }

  {
    var total = 0L
    bench("Squants", count = 5, sleep = 1000.ms) { i =>


      cfor.cfor(0)(_ < limit, _ + 1) { i =>
        val squaredSeconds = (((i % 1000) + 1).seconds ** ((i % 1000) + 1).seconds)
//        val acc:Acceleration = 10.meters divide squaredSeconds
        val acc: Acceleration = 10.meters divide squaredSeconds //i.seconds

        total += calculate_distance_1(i.ms in Seconds, acc)

      }
    }
    println(s"total: ${total}")
  }


  {
    var total = 0L
    bench("Raw Long", count = 5) { i =>

      @inline def calculate_distance(time: Long, acc: Long): Long = {
        val t = time * time
        val rr = acc * (time * time) / 2

        rr
      }

      cfor.cfor(0)(_ < limit, _ + 1) { i =>

        val acc_t = (((i % 1000) + 1) * ((i % 1000) + 1))
        val acc = 10 / acc_t //i.seconds

        total += calculate_distance((i / 1000).toLong, acc.toLong)
      }
    }
    println(s"total: ${total}")
  }
}
