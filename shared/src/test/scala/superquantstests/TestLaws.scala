package superquantstests

import org.scalatest.{FlatSpec, Matchers}
import shapeless.test.illTyped
import superquants._
import superquants.laws._
import supertagged.TaggedType

class TestLaws extends FlatSpec with Matchers {

  object DoubleTypePerKiloIntType extends TaggedType[Double]
  type DoubleTypePerKiloIntType = DoubleTypePerKiloIntType.Type

  object DoubleKiloIntType extends TaggedType[Double]
  type DoubleKiloIntType = DoubleKiloIntType.Type

  it should "work" in {

    implicit val doubleToKiloInt = Converter[DoubleType, KiloIntType]( v => KiloIntType(v.toInt * 1000) )
    implicit val kiloIntToDouble = Converter[KiloIntType, DoubleType]( v => DoubleType(v / 1000d))


    val doubleType1 = DoubleType(10d)
    val kiloIntType = doubleType1 in KiloIntType

    doubleType1 shouldEqual 10d
    kiloIntType shouldEqual 10000



    implicit val law1:Law[DoubleType, Divide, KiloIntType, DoubleTypePerKiloIntType] = Law((p1, p2) => p1 / p2)
    implicit val law2:Law[DoubleType, Multiply, KiloIntType, DoubleKiloIntType] = Law((p1, p2) => p1 * p2)


    val doubleType2 = DoubleType2(10d)

    val doubleKiloIntType = doubleType1 multiply kiloIntType // using law2
    val doubleKiloIntType2:DoubleKiloIntType = doubleType1 multiply kiloIntType

    testDoubleKiloIntType(doubleKiloIntType)
    illTyped("testDoubleType1(doubleKiloIntType)","type mismatch;.+")




//    val doubleTypePerKiloIntType = doubleType1 divide kiloIntType //using law1
//    testDoubleTypePerKiloIntType(doubleTypePerKiloIntType)



    illTyped("doubleType1 multiply 5","Not found Law to make Operation\\[superquants.Multiply\\] for.+")
    illTyped("doubleType1 ** doubleType2", "could not find implicit value for parameter d: superquants.package.FakeTrait.+")
  }


  def testDoubleType1(v:DoubleType):Unit = {}
  def testDoubleKiloIntType(v:DoubleKiloIntType):Unit = {}
  def testDoubleTypePerKiloIntType(v:DoubleTypePerKiloIntType):Unit = {}

}
