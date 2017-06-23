package superquants

trait RawEvidence[T]

object RawEvidence {

  private val stub = new RawEvidence[Nothing] {}
  private def dummy[T]:T = stub.asInstanceOf[T]

  implicit val rawEvidenceLong:RawEvidence[Long] = dummy
  implicit val rawEvidenceDouble:RawEvidence[Double] = dummy
}
