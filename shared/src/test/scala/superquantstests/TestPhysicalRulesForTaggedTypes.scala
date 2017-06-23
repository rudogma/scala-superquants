package superquantstests

import org.scalatest.{FlatSpec, Matchers}
import shapeless.Nat
import shapeless.test.illTyped
import superquants._

class TestPhysicalRulesForTaggedTypes extends FlatSpec with Matchers {

  val step1 = Step(1)
  val step2 = Step(2)

  val counter = Counter(1)

  type Squared[T] = Pow[Int, T, PowPlus, Nat._2]
  type Cubic[T] = Pow[Int, T, PowPlus, Nat._3]

  "++(plus)" should "work for taggedtype" in {

    val step3_int = step1 + step2

    step3_int shouldEqual 3
    testInt(step3_int)
    illTyped("testStep(step3_int)","type mismatch;.+")

    val step3 = step1 ++ step2

    step3 shouldEqual 3
    illTyped("step3 ++ 5","type mismatch;.+")

    testInt(step3)
    testStep(step3)
  }

  "--(minus)" should "work" in {

    val step3_int = step2 - step1

    step3_int shouldEqual 1
    testInt(step3_int)
    illTyped("testStep(step3_int)","type mismatch;.+")

    val step3 = step2 -- step1

    step3 shouldEqual 1

    testInt(step3)
    testStep(step3)

    illTyped("step2 -- counter","type mismatch;.++")
  }

  "**(multiply)" should "work for taggedtype" in {

    val step3_int = step1 * step2

    step3_int shouldEqual 2
    testInt(step3_int)
    illTyped("testStep(step3_int)")



    val squared_0:Pow[Int, Step, PowPlus, Nat._2] = step2 ** step2
    val squared_1:Squared[Step] = step2 ** step2

    squared_0 shouldEqual 4
    squared_1 shouldEqual 4
    testInt(squared_0)
    testSquared(squared_0)
    testSquared(squared_1)
    illTyped("testStep(squared_0)","type mismatch;.+")
    illTyped("testStep(squared_1)","type mismatch;.+")


    val step3 = step2 ** 5
    step3 shouldEqual 10
    testStep(step3)

  }

  "divide(divide)" should "work" in {

    val step3 = step2 divide 2

    step3 shouldEqual 1
    testStep(step3)

    val step3_int = step2 divide step2

    step3_int shouldEqual 1
    illTyped("testStep(step3_int)","type mismatch;.+")
  }


  def testInt(v:Int):Unit = {}
  def testStep(v:Step):Unit = {}

  def testSquared(v:Squared[Step]):Unit = {}
}
