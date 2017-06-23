package superquants

import shapeless.{::, HList, HNil, Nat}
import superquants.utils.SuperDummyImplicit
import supertagged.{@@, Tag, Tagged}

trait PhysicalRulesOps extends Any {

  private def cast[T, U](v: U): T = v.asInstanceOf[T]


  /**
    *
    * For top level TaggedTypes
    */
  trait PhysicalRules[Raw, U] extends Any with LowPriorityPhysicalRules[Raw, U] {
    def __v: Raw @@ U

    def ++(p2: Raw @@ U)(implicit m: RawSummer[Raw]): Raw @@ U = safecast(m(__v, p2))

    def --(p2: Raw @@ U)(implicit m: RawMinuser[Raw]): Raw @@ U = safecast(m(__v, p2))

    def **(p2: Raw @@ U)(implicit m: RawMultiplier[Raw]): Pow[Raw, Raw @@ U, PowPlus, Nat._2] = safecast(m(__v, p2))

    def divide(p2: Raw @@ U)(implicit m: RawDivider[Raw]): Raw = m(__v, p2)
  }

  private sealed trait FakeTrait[T] // Fake trait, u can't make implicit for this)

  trait LowPriorityPhysicalRules[Raw, U] extends Any {

    def safecast[T](v: Raw): T = cast(v)

    def __v: Raw @@ U

    //method for disabling other tagged types. will never compiles calling this method. and even somehow compiled - throw
    def **[U2](p2: Raw @@ U2)(implicit r: RawMultiplier[Raw], d: FakeTrait[Raw @@ U2]): Raw @@ U = ???

    //method for raw type
    def **(p2: Raw)(implicit r: RawMultiplier[Raw], d2: SuperDummyImplicit): Raw @@ U = safecast(r(__v, p2))


    //method for disabling other tagged types. will never compiles calling this method. and even somehow compiled - throw
    def divide[U2](p2: Raw @@ U2)(implicit r: RawDivider[Raw], d: FakeTrait[Raw @@ U2]): Raw @@ U = ???

    //no more need, to disable U2 with FakeTrait, because we now have Complex
    //    def divide[U2](p2: Raw @@ U2)(implicit r: RawDivider[Raw]): Complex[Raw, Pow[Raw, Raw @@ U = ???

    //method for raw type
    def divide(p2: Raw)(implicit r: RawDivider[Raw], d2: SuperDummyImplicit): Raw @@ U = safecast(r(__v, p2))


  }


  /**
    *
    * For UNITS
    */


  trait PhysicalRulesForUnits[Raw, UnitBase, U] extends Any with PhysicalRulesForUnitsLowPriority[Raw, UnitBase, U] {


    def ++(p2: Tagged[Raw, UnitBase] @@ U)(implicit m: RawSummer[Raw]): Tagged[Raw, UnitBase] @@ U = cast(m(__v, p2))

    def --(p2: Tagged[Raw, UnitBase] @@ U)(implicit m: RawMinuser[Raw]): Tagged[Raw, UnitBase] @@ U = cast(m(__v, p2))

    def **(p2: Tagged[Raw, UnitBase] @@ U)(implicit m: RawMultiplier[Raw]): Pow[Raw, Tagged[Raw, UnitBase] @@ U, PowPlus, Nat._2] = cast(m(__v, p2))




    def divide[
    Raw2 <: Raw, U2, N <: Nat,
    DiffOut <: HList
    ](p2: Pow[Raw2, U2, PowPlus, N])(
      implicit
      mixer: Mixer.Aux[
        Raw,
        Pow[Raw, Tagged[Raw, UnitBase] @@ U, PowPlus, Nat._1] :: HNil,
        Pow[Raw, U2, PowMinus, N] :: HNil,
        HNil, DiffOut
        ],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawDivider[Raw], d: SuperDummyImplicit
    )
    : cleaner.Out = cast(r(__v, p2))



    def divide[
    Raw2 <: Raw, U2, N <: Nat,
    DiffOut <: HList
    ](p2: Pow[Raw2, U2, PowMinus, N])(
      implicit
      mixer: Mixer.Aux[
        Raw,
        Pow[Raw, Tagged[Raw, UnitBase] @@ U, PowPlus, Nat._1] :: HNil,
        Pow[Raw, U2, PowPlus, N] :: HNil,
        HNil, DiffOut
        ],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawDivider[Raw], d: SuperDummyImplicit, d2: SuperDummyImplicit
    )
    : cleaner.Out = cast(r(__v, p2))



    type FF[T] = Tagged[Raw, UnitBase] @@ T


    def ===(other: Tagged[Raw, UnitBase] @@ U): Boolean = __v == other

    def ===[U2](other: Tagged[Raw, UnitBase] @@ U2)(implicit converter: UnitConverter[Raw], c1: UnitCompanion[FF[U]], c2: UnitCompanion[FF[U2]]): Boolean = converter(__v, c1, c2) == other
  }

  trait PhysicalRulesForUnitsLowPriority[Raw, UnitBase, U] extends Any with PhysicalRulesForUnitsLowPriorityFinal[Raw, UnitBase, U] {

    //For Pow[.., Plus, ..]
    def **[
    Raw0 <: Raw, U2,
    Znak <: PowZnak, N <: Nat,
    DiffOut <: HList
    ](p2: Pow[Raw0, U2, Znak, N])(
      implicit
      mixer: Mixer.Aux[
        Raw,
        Pow[Raw, Tagged[Raw, UnitBase] @@ U, PowPlus, Nat._1] :: HNil,
        Pow[Raw, U2, Znak, N] :: HNil,
        HNil, DiffOut
        ],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawMultiplier[Raw], d: SuperDummyImplicit
    ): cleaner.Out = cast(r(__v, p2))




    def divide[
    UnitBase2, U2,
    DiffOut <: HList
    ](p2: Tagged[Raw, UnitBase2] @@ U2)(
      implicit
      mixer: Mixer.Aux[Raw,
        Pow[Raw, Tagged[Raw, UnitBase], PowPlus, Nat._1] :: HNil,
        Pow[Raw, Tagged[Raw, UnitBase2] @@ U2, PowMinus, Nat._1] :: HNil,
        HNil, DiffOut
        ],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawDivider[Raw]
    ): cleaner.Out = cast(r(__v, p2))



    def divide[U2](p2: Raw @@ U2)(implicit r: RawDivider[Raw], d: FakeTrait[Raw @@ U2]): Raw @@ U = ???

    def divide(p2: Raw)(implicit r: RawDivider[Raw], d: SuperDummyImplicit, d2: SuperDummyImplicit): Tagged[Raw, UnitBase] @@ U = cast(r(__v, p2))
  }


  trait PhysicalRulesForUnitsLowPriorityFinal[Raw, UnitBase, U] extends Any {
    def __v: Tagged[Raw, UnitBase] @@ U


    def **[UnitBase2, U2](p2: Tagged[Raw, UnitBase2] @@ U2)(implicit m: RawMultiplier[Raw], d: SuperDummyImplicit)
    : Complex[Raw, Pow[Raw, Tagged[Raw, UnitBase] @@ U, PowPlus, Nat._1] :: Pow[Raw, Tagged[Raw, UnitBase2] @@ U2, PowPlus, Nat._1] :: HNil] = cast(m(__v, p2))

  }


  /** ** COMPLEX *****/

  trait PhysicalRulesForComplexTrait[Raw, Items <: HList] extends Any with PhysicalRulesForComplexLowPriority[Raw, Items] {

    def **[
    Raw0 <: Raw, UnitBase, U,
    NewItems <: HList, DiffOut <: HList
    ](p2: Tagged[Raw0, UnitBase] @@ U)(
      implicit
      mixer: Mixer.Aux[Raw, Items, Pow[Raw, Tagged[Raw, UnitBase] @@ U, PowPlus, Nat._1] :: HNil, HNil, DiffOut],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawMultiplier[Raw]
    )
    : cleaner.Out = cast(r(__v, p2))


    def **[NewItems <: HList, DiffOut <: HList](p2: Complex[Raw, NewItems])(
      implicit
      mixer: Mixer.Aux[Raw, Items, NewItems, HNil, DiffOut],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawMultiplier[Raw],
      d: SuperDummyImplicit
    )
    : cleaner.Out = cast(r(__v, p2))


    def divide[
    Raw0 <: Raw, UnitBase, U,
    NewItems <: HList, DiffOut <: HList
    ](p2: Tagged[Raw0, UnitBase] @@ U)(
      implicit
      mixer: Mixer.Aux[Raw, Items, Pow[Raw, Tagged[Raw, UnitBase] @@ U, PowMinus, Nat._1] :: HNil, HNil, DiffOut],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawMultiplier[Raw]
    )
    : cleaner.Out = cast(r(__v, p2))


  }

  trait PhysicalRulesForComplexLowPriority[Raw, Items <: HList] extends Any with PhysicalRulesForComplexLowPriorityFinal[Raw, Items] {

    /**
      * Pow[...]
      */
    def **[
    UnitBase, U, Znak <: PowZnak, N <: Nat,
    DiffOut <: HList
    ](p2: Pow[Raw, Tagged[Raw, UnitBase] @@ U, Znak, N])(
      implicit
      mixer: Mixer.Aux[Raw, Items, Pow[Raw, Tagged[Raw, UnitBase] @@ U, Znak, N] :: HNil, HNil, DiffOut],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawMultiplier[Raw],
      d: SuperDummyImplicit
    ):
    cleaner.Out = cast(r(__v, p2))


    def divide[
    Raw0 <: Raw, U, N <: Nat,
    DiffOut <: HList
    ](p2: Pow[Raw0, U, PowPlus, N])(
      implicit
      mixer: Mixer.Aux[Raw, Items, Pow[Raw, U, PowMinus, N] :: HNil, HNil, DiffOut],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawDivider[Raw],
      d: SuperDummyImplicit
    ): cleaner.Out = cast(r(__v, p2))

    def divide[
    Raw0 <: Raw, U, N <: Nat,
    DiffOut <: HList
    ](p2: Pow[Raw0, U, PowMinus, N])(
      implicit
      mixer: Mixer.Aux[Raw, Items, Pow[Raw, U, PowPlus, N] :: HNil, HNil, DiffOut],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawDivider[Raw],
      d: SuperDummyImplicit, d2: SuperDummyImplicit
    ): cleaner.Out = cast(r(__v, p2))


    def divide[NewItems <: HList, NegatedItems <: HList, DiffOut <: HList](p2: Complex[Raw, NewItems])(
      implicit
      r: RawDivider[Raw],
      negate:Negate.Aux[Raw, NewItems, NegatedItems],
      mixer: Mixer.Aux[Raw, Items, NegatedItems, HNil, DiffOut],
      cleaner: Cleaner[Raw, DiffOut]
    ): cleaner.Out = cast(r(__v, p2))



    def divide[U <: Tag[_, _]](p2: Raw with U)(implicit r2: FakeTrait[Raw with U]): Complex[Raw, Items] = ???

    def divide[Raw2 <: Raw](p2: Raw2)(implicit r: RawDivider[Raw]): Complex[Raw, Items] = cast(r(__v, p2))
  }

  trait PhysicalRulesForComplexLowPriorityFinal[Raw, Items <: HList] extends Any {

    def __v: Raw with Complex[Raw, Items]
  }


  trait PhysicalRulesForPowTrait[Raw,U,Znak <: PowZnak, N <: Nat] extends Any with PhysicalRulesForPowFinal[Raw,U,Znak,N]{

    /**
      * Pow[...]
      */
    def **[
    Raw2 <: Raw, U2, Znak2 <: PowZnak, N2 <: Nat,
    DiffOut <: HList
    ](p2: Pow[Raw2, U2, Znak2, N2])(
      implicit
      mixer: Mixer.Aux[
        Raw,
        Pow[Raw, U, Znak, N] :: HNil,
        Pow[Raw, U2, Znak2, N2] :: HNil,
        HNil, DiffOut
        ],
      cleaner: Cleaner[Raw, DiffOut],
      r: RawMultiplier[Raw], d: SuperDummyImplicit
    ): cleaner.Out = cast(r(__v, p2))


  }

  trait PhysicalRulesForPowFinal[Raw,U,Znak <: PowZnak, N <: Nat] extends Any {
    def __v:Pow[Raw, U, Znak, N]
  }

}
