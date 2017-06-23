package superquants.traits

import superquants._
import superquants.MetricSystem._


trait LengthTrait {


  type Nanometers
  type Millimeters
  type Centimeters
  type Decimeters
  type Meters
  type Kilometers
  type InternationalMiles


  implicit val cInterMile = UnitCompanion[InternationalMiles](1.609344e3, "Mile", "mile")

  implicit val cNano = UnitCompanion[Nanometers](Nano, "Nanometre", "nm")
  implicit val cMillimeter = UnitCompanion[Millimeters](Milli, "Millimeter", "mm")
  implicit val cCentimeter = UnitCompanion[Centimeters](Centi, "Centimeter", "cm")
  implicit val cDecimeter = UnitCompanion[Decimeters](Deci, "Decimeter", "dm")
  implicit val cMeter = UnitCompanion[Meters](Uno, "Meter", "m")
  implicit val cKilometer = UnitCompanion[Kilometers](Kilo, "Kilometer", "km")



  trait LengthForRawOpsTrait[Raw] extends Any {

    protected def safecasted[T]: T

    def nm: Nanometers = safecasted

    def nanometers: Nanometers = safecasted

    def mm: Millimeters = safecasted
    def millimeters: Millimeters = safecasted

    def cm: Centimeters = safecasted

    def centimeters: Centimeters = safecasted

    def dm: Decimeters = safecasted

    def decimeters: Decimeters = safecasted

    def m: Meters = safecasted

    def meters: Meters = safecasted

    def km: Kilometers = safecasted

    def kilometers: Kilometers = safecasted
  }



}
