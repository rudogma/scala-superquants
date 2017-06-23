package superquants.render

import superquants._

trait ShowerCompanions {

  trait ShowerCompanion[T] {
    type C = UnitCompanion[_]

    def apply(v:T, cOrigin:C, cCurrent:C):(T,T)
    def empty(v:T):Boolean
    def lt1(v:T):Boolean

    def ceil(v:T):Long
  }

  implicit val showerCompanionInt:ShowerCompanion[Int] = new ShowerCompanion[Int] {
    def apply(v:Int, cOrigin:C, cCurrent:C):(Int,Int) = {
      val v1 = (v * cOrigin.ratio / cCurrent.ratio).toInt
      val v2 = (v % (cCurrent.ratio / cOrigin.ratio)).toInt

      (v1, v2)
    }

    def empty(v:Int) = v == 0

    def lt1(v:Int):Boolean = v < 1
    def ceil(v:Int):Long = v.toLong
  }

  implicit val showerCompanionLong:ShowerCompanion[Long] = new ShowerCompanion[Long] {
    def apply(v:Long, cOrigin:C, cCurrent:C):(Long,Long) = {
      val v1 = (v * cOrigin.ratio / cCurrent.ratio).toLong
      val v2 = (v % (cCurrent.ratio / cOrigin.ratio)).toLong

      (v1, v2)
    }

    def empty(v:Long) = v == 0
    def lt1(v:Long):Boolean = v < 1
    def ceil(v:Long):Long = v
  }

  implicit val showerCompanionDouble:ShowerCompanion[Double] = new ShowerCompanion[Double] {
    def apply(v:Double, cOrigin:C, cCurrent:C):(Double,Double) = {
      val v1 = (v * cOrigin.ratio / cCurrent.ratio).toDouble
      val v2 = (v % (cCurrent.ratio / cOrigin.ratio)).toDouble

      (v1, v2)
    }

    def empty(v:Double) = v == 0
    def lt1(v:Double):Boolean = v < 1
    def ceil(v:Double):Long = v.toLong
  }
}
