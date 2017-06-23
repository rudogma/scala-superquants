package superquants.longprecision

import superquants.traits.MassTrait
import supertagged._

import scala.language.higherKinds

package object mass extends MassTrait with LongMassTypesTrait {




  implicit class LongMassForRawOpsInt(val __v:Int) extends AnyVal with MassForRawOpsTrait[Int] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }

  implicit class LongMassForRawOpsLong(val __v:Long) extends AnyVal with MassForRawOpsTrait[Long] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }


}

trait LongMassTypesTrait {

  object MassUnit extends TaggedType[Long]
  //Tagged[Long,MassUnit.Tag] @@ T
  type MassUnit[T] = (Long with Tag[Long,MassUnit.Tag]) @@ T

  object Micrograms extends OverTagged(MassUnit)
  type Micrograms = Micrograms.Type

  object Milligrams extends OverTagged(MassUnit)
  type Milligrams = Milligrams.Type

  object Grams extends OverTagged(MassUnit)
  type Grams = Grams.Type

  object Kilograms extends OverTagged(MassUnit)
  type Kilograms = Kilograms.Type

  object Tonnes extends OverTagged(MassUnit)
  type Tonnes = Tonnes.Type

  object Ounces extends OverTagged(MassUnit)
  type Ounces = Ounces.Type

  object Pounds extends OverTagged(MassUnit)
  type Pounds = Pounds.Type

  object Kilopounds extends OverTagged(MassUnit)
  type Kilopounds = Kilopounds.Type

  object Megapounds extends OverTagged(MassUnit)
  type Megapounds = Megapounds.Type

  object Stone extends OverTagged(MassUnit)
  type Stone = Stone.Type

  object TroyGrains extends OverTagged(MassUnit)
  type TroyGrains = TroyGrains.Type

  object Pennyweights extends OverTagged(MassUnit)
  type Pennyweights = Pennyweights.Type

  object TroyOunces extends OverTagged(MassUnit)
  type TroyOunces = TroyOunces.Type

  object TroyPounds extends OverTagged(MassUnit)
  type TroyPounds = TroyPounds.Type

  object Tolas extends OverTagged(MassUnit)
  type Tolas = Tolas.Type

  object Carats extends OverTagged(MassUnit)
  type Carats = Carats.Type

  object SolarMasses extends OverTagged(MassUnit)
  type SolarMasses = SolarMasses.Type
}