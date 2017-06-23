package superquantstests

import org.scalatest.{FlatSpec, Matchers}
import shapeless.test.illTyped
import superquants._

class TestEquality extends FlatSpec with Matchers {


  "Longprecision" should "work" in {

    import longprecision.mass._
    import longprecision.time._

    val kg = 5.kg
    val g = 5.g

    val g2 = 5000.g
    val kg2 = g2 in Kilograms


    (kg == 5) shouldBe true

    //Warning!!! Feature-not-bug
    (kg == g) shouldBe true

    (kg === g) shouldBe false


    (kg === g2) shouldBe true
    (kg === kg2) shouldBe true


    val s = 5.seconds

    illTyped( "kg === s","overloaded method value === .+ cannot be applied to.+")
  }
}
