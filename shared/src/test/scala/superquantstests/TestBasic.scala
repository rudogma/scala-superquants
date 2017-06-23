package superquantstests

import org.scalatest.{FlatSpec, Matchers}
import shapeless._
import shapeless.test.illTyped
import superquants._
import superquants.longprecision.mass._
import superquants.longprecision.time._
import superquants.render._
import supertagged.{@@, Tag, Tagged}

import scala.language.implicitConversions
import scala.annotation.implicitNotFound

class TestBasic extends FlatSpec with Matchers {



  "Test some mass" should "work" in {


    val kg = 10.kg
    val grams = kg in Grams

    kg shouldEqual 10
    grams shouldEqual 10000

    kg.pretty shouldEqual "10 kg"
    grams.pretty shouldEqual "10000 g"


    val kg2 = grams in Kilograms

    kg2 shouldEqual 10
    kg2.pretty shouldEqual "10 kg"


    val kg3:Kilograms = grams in Kilograms // Explicitly specify return type. Just should compile

    kg3 shouldEqual 10
    kg3.pretty shouldEqual "10 kg"




    11123456789L.mg.pretty[Tonnes :: Kilograms :: Grams :: Milligrams :: HNil] shouldEqual "11 t 123 kg 456 g 789 mg"

    11123456789L.mg.pretty[Tonnes :: Milligrams :: HNil] shouldEqual "11 t 123456789 mg"

    illTyped("grams in Seconds","could not find implicit value for parameter c2: superquants.package.UnitCompanion.+")
    illTyped("grams.pretty[Seconds :: HNil]","Can't pretty with units from different families.+")

  }

  def testKg(kg:Kilograms): Unit ={

  }

}