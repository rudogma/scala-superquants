package superquants.utils

sealed trait SuperDummyImplicit

object SuperDummyImplicit {

  // def => val for re-use
  implicit val superDummyImplicit: SuperDummyImplicit = new SuperDummyImplicit{}
}
