
[![Build status](https://img.shields.io/travis/Rudogma/scala-superquants/master.svg)](https://travis-ci.org/Rudogma/scala-superquants)
[![Maven Central](https://img.shields.io/maven-central/v/org.rudogma/superquants_2.12.svg)](https://maven-badges.herokuapp.com/maven-central/org.rudogma/superquants_2.12)

# Typelevel unboxed compile time dimensional analysis over tagged types.


## sbt

**Scala: 2.11.11, 2.12.1, 2.12.2**
```scala
libraryDependencies += "org.rudogma" %% "superquants" % "0.9"
```

**ScalaJS (compiled with 0.6.17)**
```scala
libraryDependencies += "org.rudogma" %%% "superquants" % "0.9"
```

# Features

- Intellij Idea compatible 100%. Red marks free.
- Typelevel (no boxing for primitives)
- Compile-time
- Using tagged types (library: [https://github.com/Rudogma/scala-supertagged](https://github.com/Rudogma/scala-supertagged))
- Supported physical rules (examples below)
- Auto-plaining after working with complex types (example below)
- Long & Double precision included
- Extended prettify ops
- Support for aliasing complex types. (Like Acceleration or any other)
- Predefined aliases for power (Single, Squared, Cubic & their negative variants)
- Predefined Units
    - Length (7 units)
    - Time (8 units)
    - Mass (17 units)
    - Binary (19 units)
- Performance 
    - There is no any additional garbage for GC! All implicit evidences are reuse singleton objects.
    - There is no boxing, so they are much faster then AnyVal, but there is some boilerplate-bytecode, so they are slower then raw primitives without tags. This boilerplate can be removed with micro-scalac-plugin and then there will be no difference between this library and raw primitives.
    - Long precision ( ~10x slower on big formulas, than primitive Long)(Slower then Double precision, because additional convertions Double -> Long applied)
    - Double precision( ~2-4x slower on big formulas, than primitive Double)( But! It is 7-10x FASTER then based on AnyVal: [https://github.com/typelevel/squants](https://github.com/typelevel/squants) and still has no garbage for GC)
- Define your own unit(or extend predefined) in a few lines and automatically get supported all physical rules (see predefined units in sources. For example: shared/src/main/scala/superquants/longprecision/time/package.scala)
    
    
# Roadmap

This is a snapshot of pre-release. Release planned at **July'2017**
For now almost all physical rules supported for plain, powered and complex(fractional) units. However there some cases that i didn't test yet. I will check them all in a few weeks. Also I plan to add some more features before release to power up usability.

# Usage

###Check out tests for more examples

### Basic terms

I think you already know what is `Tagged types` and how they work ( or U can read some more at: [https://github.com/Rudogma/scala-supertagged](https://github.com/Rudogma/scala-supertagged) ). In `supertagged` I've made new level of tagging and called this `OverTagged`. `TaggedType` and `OverTagged` - are used upon this library heavily.

Define some val(-r)s
```scala
import superquants._
import longprecision._
import longprecision.length._
import longprecision.time._
import shapeless.{ ::, HNil, Nat }

val meters:Meters = 5.meters
val seconds:Seconds = Seconds @@ (Time @@ 5L)
val squaredSeconds:Pow[Long, Seconds, PowPlus, Nat._1] = 5L.as[Pow[Long, Seconds, PowPlus, Nat._1]]
// or using alias
val squaredSeconds2:Squared[Seconds] = 5L.as[Squared[Seconds]]

val speed:Complex[Long, Pow[Long, Meters, PowPlus, Nat._1] :: Pow[Long,Seconds,PowMinus, Nat._1]] = 5L.as //as[T] - will auto use explicit type from the left

//aliased
val speed2:Complex[Long, Single[Meters] :: Negative.Single[Seconds] :: HNil] = 5L.as

type Speed = Complex[Long, Single[Meters] :: Negative.Single[Seconds] :: HNil]

val speed22:Speed = 5L.as
/**
* Library based on a simple physical rules. So... 
*/

val metersXseconds = 5.meters ** 5.seconds
val speed3:Speed = 5.meters divide (5.seconds ** 5.seconds)

val plainMeters:Meters = speed3 ** (5.seconds ** 5.seconds)

// annihilate units
val plainLong = speed3 ** (5.seconds ** 5.seconds divide 5.meters)
 
 val meters2:Meters = 5.meters ++ 5.meters
 //but, U can't do it with primitive
 // meters2 = 5.meters ++ 5L // Wil not compile!!! Physicists denied that!
 //but, U can multiply unit with raw primitives
 val meters3:Meters = 5.meters ** 5L

```

### Limitations

The cost of performance:
As U see above I've used '**' for multipying. Because our types are tagged on type level(and so unboxed) and have no materialized mixins at runtime we can't overload primitive operators.
Supported operators:
- **`--`**
- **`++`**
- **`**`**
- **`divide`** //for `/`, because `//` - starts comment :)

##### Note!

Because our types are primitives. Primitive operators also works and provide raw primitive value

```scala
val meters:Meters = 5.meters * 5.seconds // fails, because we got raw Long. But compiler sees Meters at the left and will safe us. 
```

Physicists agree. If we multiply meters on seconds we will get just number. Not seconds, not meters, not other complex unit. Just Number. These remains all primitive operators.


### Aliasing
```scala
import superquants._
import longprecision._
import longprecision.length._
import longprecision.time._
import shapeless.{::, HNil}

//Using aliases in  longprecision.package.scala
type Acceleration = Complex[Long, Single[Meters] :: Squared[Seconds] :: HNil]
//Equivalent in a more verbose: type Acceleration = Complex[Long, Pow[Long, Meters, PowPlus, Nat._1] :: Pow[Long, Seconds, PowMinus, Nat._2] :: HNil]

val acc = 5.meters divide (5.seconds ** 5.seconds)

//Or we can explicitly specify alias if we think we could forget something. Let compiler do some work!
val acc2:Acceleration = acc 

// will accept only accelerations
def onlyAcceleration(acc:Acceleration):Unit = {}

```


### Convert

```scala
import superquants._
import longprecision._

val mg:Milligrams = 5.kg in Grams // Got: 5000 Milligrams

```


### Prettify
```scala
import superquants._
import render._
import longprecision.mass._


10.kg.pretty shouldEqual "10 kg"

//Or U can specify how much details u need
11123456789L.mg.pretty[Tonnes :: Kilograms :: Grams :: Milligrams :: HNil] shouldEqual "11 t 123 kg 456 g 789 mg"
11123456789L.mg.pretty[Tonnes :: Milligrams :: HNil] shouldEqual "11 t 123456789 mg"


```
