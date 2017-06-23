package superquants.traits

import supertagged.{@@, Tag, TaggedType, OverTagged}
import superquants.{UnitCompanion}
import superquants.MetricSystem._

trait MassTrait {

  type Micrograms
  type Milligrams
  type Grams
  type Kilograms
  type Tonnes
  type Ounces
  type Pounds
  type Kilopounds
  type Megapounds
  type Stone
  type TroyGrains
  type Pennyweights
  type TroyOunces
  type TroyPounds
  type Tolas
  type Carats
  type SolarMasses


  implicit val cMicrograms: UnitCompanion[Micrograms] = UnitCompanion[Micrograms](Micro, "Microgram", "mcg")
  implicit val cMilligrams: UnitCompanion[Milligrams] = UnitCompanion[Milligrams](Milli, "Milligram", "mg")
  implicit val cGrams: UnitCompanion[Grams] = UnitCompanion[Grams](Uno, "Gram", "g")
  implicit val cKilograms: UnitCompanion[Kilograms] = UnitCompanion[Kilograms](Kilo, "Kilogram", "kg")
  implicit val cTonnes: UnitCompanion[Tonnes] = UnitCompanion[Tonnes](Mega, "Tonne", "t")


  implicit val cPounds: UnitCompanion[Pounds] = UnitCompanion[Pounds](cKilograms.ratio * 4.5359237e-1, "Pound", "lb")
  implicit val cOunces: UnitCompanion[Ounces] = UnitCompanion[Ounces](cPounds.ratio / 16d, "Ounce", "oz")
  implicit val cKilopounds: UnitCompanion[Kilopounds] = UnitCompanion[Kilopounds](cPounds.ratio * Kilo, "Kilopound", "klb")
  implicit val cMegapounds: UnitCompanion[Megapounds] = UnitCompanion[Megapounds](cPounds.ratio * Kilo, "Megapound", "Mlb")
  implicit val cStone: UnitCompanion[Stone] = UnitCompanion[Stone](cPounds.ratio * 14d, "Ston", "st")
  implicit val cTroyGrains: UnitCompanion[TroyGrains] = UnitCompanion[TroyGrains](64.79891 * cMilligrams.ratio, "TroyGrain", "gr")
  implicit val cPennyweights: UnitCompanion[Pennyweights] = UnitCompanion[Pennyweights](24d * cTroyGrains.ratio, "Pennyweight", "dwt")
  implicit val cTroyOunces: UnitCompanion[TroyOunces] = UnitCompanion[TroyOunces](480d * cTroyGrains.ratio, "TroyOunce", "oz ")
  implicit val cTroyPounds: UnitCompanion[TroyPounds] = UnitCompanion[TroyPounds](12d * cTroyOunces.ratio, "TroyPound", "lb ")
  implicit val cTolas: UnitCompanion[Tolas] = UnitCompanion[Tolas](180d * cTroyGrains.ratio, "Tola", "tola")
  implicit val cCarats: UnitCompanion[Carats] = UnitCompanion[Carats](200d * cMilligrams.ratio, "Carat", "ct")
  implicit val cSolarMasses:UnitCompanion[SolarMasses] = UnitCompanion[SolarMasses](1.98855e33, "SolarMasse", "M")



  trait MassForRawOpsTrait[Raw] extends Any {

    protected def safecasted[T]:T

    def mg:Milligrams = safecasted

    def g:Grams = safecasted
    def grams:Grams = safecasted
    def kg:Kilograms = safecasted

  }
}
