package superquants.traits

import superquants.UnitCompanion
import superquants.MetricSystem._

trait BinaryTrait {


  type Bits
  type Bytes
  type KiloBytes
  type MegaBytes
  type GigaBytes
  type TeraBytes
  type PetaBytes
  type ExaBytes
  type ZettaBytes
  type YottaBytes
  type KibiBytes
  type MebiBytes
  type GibiBytes
  type TebiBytes
  type PebiBytes
  type ExbiBytes
  type ZebiBytes
  type YobiBytes




  implicit val cBits = UnitCompanion[Bits](1/8.toDouble, "Bit","b")

  implicit val cBytes = UnitCompanion[Bytes](Uno, "Byte", "b")
  implicit val cKiloBytes = UnitCompanion[KiloBytes](Kilo, "Kilobyte", "KB")
  implicit val cMegaBytes = UnitCompanion[MegaBytes](Mega, "Megabyte", "MB")
  implicit val cGigaBytes = UnitCompanion[GigaBytes](Giga, "Gigabyte", "GB")
  implicit val cTeraBytes = UnitCompanion[TeraBytes](Tera, "Terabyte", "TB")
  implicit val cPetaBytes = UnitCompanion[PetaBytes](Peta, "Petabyte", "PB")
  implicit val cExaBytes = UnitCompanion[ExaBytes](Exa, "Exabyte", "EB")
  implicit val cZettaBytes = UnitCompanion[ZettaBytes](Zetta, "Zettabyte", "ZB")
  implicit val cYottaBytes = UnitCompanion[YottaBytes](Yotta, "Yottabyte", "YB")





  implicit val cKibiBytes = UnitCompanion[KibiBytes](1024 , "Kibibyte", "KiB")
  implicit val cMebiBytes = UnitCompanion[MebiBytes](Math.pow(1024, 2) , "Mebibyte", "MiB")
  implicit val cGibiBytes = UnitCompanion[GibiBytes](Math.pow(1024, 3) , "Gibibyte", "GiB")
  implicit val cTebiBytes = UnitCompanion[TebiBytes](Math.pow(1024, 4) , "Tebibyte", "TiB")
  implicit val cPebiBytes = UnitCompanion[PebiBytes](Math.pow(1024, 5) , "Pebibyte", "PiB")
  implicit val cExbiBytes = UnitCompanion[ExbiBytes](Math.pow(1024, 6) , "Exbibyte", "EiB")
  implicit val cZebiBytes = UnitCompanion[ZebiBytes](Math.pow(1024, 7) , "Zebibyte", "ZiB")
  implicit val cYobiBytes = UnitCompanion[YobiBytes](Math.pow(1024, 8) , "Yobibyte", "YiB")



  trait BinaryForRawOpsTrait[Raw] extends Any {

    protected def safecasted[T]:T

    def bytes:Bytes = safecasted
    def kb:KiloBytes = safecasted
    def mb:MegaBytes = safecasted


    def mib:MebiBytes = safecasted
  }

}
