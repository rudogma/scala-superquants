package superquantstests

import shapeless.{HNil, Nat, ::}
import spire.syntax.cfor
import spire.syntax.cfor
import superquants._
import doubleprecision._
import doubleprecision.time._
import doubleprecision.length._

/**
  *
  *
  *
Starting: 10kk
[run#0] ended in 783ms
[run#1] ended in 563ms
[run#2] ended in 363ms
[run#3] ended in 343ms
[run#4] ended in 342ms
[bench][Squants][runs=5] ended in 2394ms
total: 13697389739390
[run#0] ended in 142ms
[run#1] ended in 131ms
[run#2] ended in 146ms
[run#3] ended in 139ms
[run#4] ended in 147ms
[bench][Raw Double][runs=5] ended in 705ms
total: 13697389739390


Starting: 100kk (7x faster vs https://github.com/typelevel/squants/)
[run#0] ended in 3678ms
[run#1] ended in 3398ms
[run#2] ended in 3288ms
[run#3] ended in 3304ms
[run#4] ended in 3332ms
[bench][Squants][runs=5] ended in 17000ms
total: 13699250442030740
[run#0] ended in 1387ms
[run#1] ended in 1316ms
[run#2] ended in 1424ms
[run#3] ended in 1429ms
[run#4] ended in 1431ms
[bench][Raw Double][runs=5] ended in 6987ms
total: 13699250442030740
[success] Total time: 29 s, completed 22.06.2017 19:13:14



 100kk with https://github.com/typelevel/squants/
[run#0] ended in 22077ms
[run#1] ended in 23036ms
[run#2] ended in 23125ms
[run#3] ended in 22907ms
[run#4] ended in 23469ms
[bench][default][runs=5] ended in 114614ms
total: 13699248983174360




  *
  */
object BenchDoublePrecision extends App {

  var limit = 1000 * 1000 * 10 //0

  Thread.sleep( (1.seconds in Millis).toLong)

  println(s"Starting: $limit")


  type Acceleration = Complex[Double, Single[Meters] :: Negative.Squared[Seconds] :: HNil]

  @inline def calculate_distance_1(time: Seconds, acc: Acceleration): Meters = {
    val squaredTime = time ** time
    val rr = acc ** squaredTime divide 2d


    rr
  }

  {
    var total = 0L
    bench("Squants", count = 5) { i =>


      cfor.cfor(0)(_ < limit, _ + 1) { i =>
        val squaredSeconds = (((i % 1000) + 1).seconds ** ((i % 1000) + 1).seconds)
        val acc: Acceleration = 10.meters divide squaredSeconds //i.seconds

        total += calculate_distance_1(i.ms in Seconds, acc).toLong

      }
    }
    println(s"total: ${total}")
  }


  {
    var total = 0L
    bench("Raw Double", count = 5) { i =>

      @inline def calculate_distance(time: Double, acc: Double): Double = {
        val t = time * time
        val rr = acc * (time * time) / 2

        rr
      }

      cfor.cfor(0)(_ < limit, _ + 1) { i =>

        val squaredSeconds = (((i % 1000) + 1).toDouble * ((i % 1000) + 1).toDouble)
        val acc = 10 / squaredSeconds //i.seconds

        total += calculate_distance(i / 1000.toDouble, acc).toLong
      }
    }
    println(s"total: ${total}")
  }
}
