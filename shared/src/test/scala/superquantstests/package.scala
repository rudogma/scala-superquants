import shapeless.test.illTyped
import superquants.longprecision.time._
import supertagged.{@@, TaggedType, TaggedTypeF}


package object superquantstests {

  object Step extends TaggedType[Int]
  type Step = Step.Type

  object StepLong extends TaggedType[Long]
  type StepLong = StepLong.Type

  object Counter extends TaggedType[Int]
  type Counter = Counter.Type


  object DoubleType extends TaggedType[Double]
  type DoubleType = DoubleType.Type

  object DoubleType2 extends TaggedType[Double]
  type DoubleType2 = DoubleType2.Type

  object KiloIntType extends TaggedType[Int]
  type KiloIntType = KiloIntType.Type


  /** MICRO BENCH **/

  object BenchIndex extends TaggedType[Int]
  type BenchIndex = BenchIndex.Type


  def bench(title:String = "default", count:Int=1, sleep:Milliseconds = 100.millis)(f: BenchIndex => Unit): Long ={


    var measures = List.empty[Long]
    var i = 0

    while( i < count){


      val started_at = System.currentTimeMillis()

      f(BenchIndex @@ i)

      val duration = System.currentTimeMillis() - started_at

      println(s"[run#${i}] ended in ${duration}ms")

      measures = measures :+ duration
      i = i + 1



      if(sleep > 0){
        Thread.sleep(sleep)
      }
    }


//    for( (measure,index) <- measures.zipWithIndex){
//      println(s"[run#${index}] ended in ${measure}ms")
//    }

    val total_duration = measures.sum

    println(s"[bench][${title}][runs=$count] ended in ${total_duration}ms")

    total_duration
  }
}
