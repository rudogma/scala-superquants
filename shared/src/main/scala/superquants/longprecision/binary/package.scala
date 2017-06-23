package superquants.longprecision

import superquants.traits.BinaryTrait
import supertagged._

package object binary extends BinaryTrait with LongBinaryTypesTrait {


  implicit class LongBinaryForRawOpsInt(val __v:Int) extends AnyVal with BinaryForRawOpsTrait[Int] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }

  implicit class LongBinaryForRawOpsLong(val __v:Long) extends AnyVal with BinaryForRawOpsTrait[Long] {
    protected def safecasted[T]: T = __v.toLong.asInstanceOf[T]
  }

}

trait LongBinaryTypesTrait {
  object BinaryUnit extends TaggedType[Long]

  type BinaryUnit[T] = (Long with Tag[Long,BinaryUnit.Tag]) @@ T


  object Bits extends OverTagged(BinaryUnit)
  type Bits = Bits.Type

  object Bytes extends OverTagged(BinaryUnit)
  type Bytes = Bytes.Type

  object KiloBytes extends OverTagged(BinaryUnit)
  type KiloBytes = KiloBytes.Type


  object MegaBytes extends OverTagged(BinaryUnit)
  type MegaBytes = MegaBytes.Type

  object GigaBytes extends OverTagged(BinaryUnit)
  type GigaBytes = GigaBytes.Type

  object TeraBytes extends OverTagged(BinaryUnit)
  type TeraBytes = TeraBytes.Type

  object PetaBytes extends OverTagged(BinaryUnit)
  type PetaBytes = PetaBytes.Type

  object ExaBytes extends OverTagged(BinaryUnit)
  type ExaBytes = ExaBytes.Type

  object ZettaBytes extends OverTagged(BinaryUnit)
  type ZettaBytes = ZettaBytes.Type

  object YottaBytes extends OverTagged(BinaryUnit)
  type YottaBytes = YottaBytes.Type


  /**
    * PIBIDI
    **/

  object KibiBytes extends OverTagged(BinaryUnit)
  type KibiBytes = KibiBytes.Type


  object MebiBytes extends OverTagged(BinaryUnit)
  type MebiBytes = MebiBytes.Type

  object GibiBytes extends OverTagged(BinaryUnit)
  type GibiBytes = GibiBytes.Type

  object TebiBytes extends OverTagged(BinaryUnit)
  type TebiBytes = TebiBytes.Type

  object PebiBytes extends OverTagged(BinaryUnit)
  type PebiBytes = PebiBytes.Type

  object ExbiBytes extends OverTagged(BinaryUnit)
  type ExbiBytes = ExbiBytes.Type

  object ZebiBytes extends OverTagged(BinaryUnit)
  type ZebiBytes = ZebiBytes.Type

  object YobiBytes extends OverTagged(BinaryUnit)
  type YobiBytes = YobiBytes.Type
}