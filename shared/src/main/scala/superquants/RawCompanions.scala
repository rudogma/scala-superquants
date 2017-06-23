package superquants

trait RawCompanions {

  trait RawSummer[T] {
    def apply(p1:T, p2:T):T
  }

  implicit val rawSummerInt:RawSummer[Int] = new RawSummer[Int] { def apply(p1:Int,p2:Int) = p1 + p2 }
  implicit val rawSummerLong:RawSummer[Long] = new RawSummer[Long] { def apply(p1:Long,p2:Long) = p1 + p2 }
  implicit val rawSummerDouble:RawSummer[Double] = new RawSummer[Double] { def apply(p1:Double,p2:Double) = p1 + p2 }


  trait RawMinuser[T] {
    def apply(p1:T, p2:T):T
  }

  implicit val rawMinuserInt:RawMinuser[Int] = new RawMinuser[Int] { def apply(p1:Int,p2:Int) = p1 - p2 }
  implicit val rawMinuserLong:RawMinuser[Long] = new RawMinuser[Long] { def apply(p1:Long,p2:Long) = p1 - p2 }
  implicit val rawMinuserDouble:RawMinuser[Double] = new RawMinuser[Double] { def apply(p1:Double,p2:Double) = p1 - p2 }


  trait RawMultiplier[T] {
    def apply(p1:T, p2:T):T
  }

  implicit val rawMultiplierForInt:RawMultiplier[Int] = new RawMultiplier[Int] { def apply(p1: Int, p2: Int) = p1 * p2 }
  implicit val rawMultiplierForLong:RawMultiplier[Long] = new RawMultiplier[Long] { def apply(p1: Long, p2: Long) = p1 * p2 }
  implicit val rawMultiplierForDouble:RawMultiplier[Double] = new RawMultiplier[Double] { def apply(p1: Double, p2: Double) = p1 * p2 }


  trait RawDivider[T] {
    def apply(p1:T, p2:T):T
  }

  implicit val rawDividerForInt:RawDivider[Int] = new RawDivider[Int] { def apply(p1:Int,p2:Int) = p1 / p2 }
  implicit val rawDividerForLong:RawDivider[Long] = new RawDivider[Long] { def apply(p1:Long,p2:Long) = p1 / p2 }
  implicit val rawDividerForDouble:RawDivider[Double] = new RawDivider[Double]{ def apply(p1:Double,p2: Double) = p1 / p2 }

}
