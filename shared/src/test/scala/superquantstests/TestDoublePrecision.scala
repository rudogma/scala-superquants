package superquantstests

import org.scalatest.{FlatSpec, Matchers}
import shapeless._
import shapeless.test.illTyped
import superquants._
import superquants.render._
import superquants.doubleprecision.time._
import superquants.doubleprecision.length._

class TestDoublePrecision extends FlatSpec with Matchers {


  "DoublePrecision[TimeUnit]" should "work" in {

    val sec = 100.seconds
    val m = sec in Minutes

    sec.pretty shouldEqual "100.0 s"

    m shouldEqual (100 / 60d)
    m.pretty shouldEqual "1.6666666666666667 m"


    (m in Seconds) shouldEqual 100.0
    (m in Seconds).pretty shouldEqual "100.0 s"



    val mmeters = 2350025.mm
    val km = mmeters in Kilometers


    val meters = mmeters in Meters


    mmeters.pretty[Kilometers :: Meters :: Millimeters :: HNil] shouldEqual "2 km 350 m 25.0 mm"



    illTyped("""mmeters.pretty[Seconds :: HNil]""","Can't pretty with units from different families.+")
    illTyped("""mmeters.pretty[Kilometers :: Meters :: Seconds :: HNil]""","Can't pretty with units from different families.+")


  }
}
