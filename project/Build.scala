import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._


object Versions {
  val Superquants = "0.9"
  val Scala = "2.12.2"
  val ScalaCross = Seq("2.12.2", "2.11.11")

}

object Project {
  val defaultSettings = Seq(

    organization in ThisBuild := "org.rudogma",
    name := "superquants",
    version in ThisBuild := Versions.Superquants,

    licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),

    homepage := Some(url("https://github.com/Rudogma/scala-superquants")),

    libraryDependencies ++= Seq(
      "org.rudogma" %% "supertagged" % "1.2",
      "com.chuusai" %% "shapeless" % "2.3.2"
    )
  )

}

object Compiler {

  val defaultSettings = Seq(
    scalacOptions in ThisBuild ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked",
      "-Xfatal-warnings",
      "-Yno-adapted-args",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-unused-import"
    ),

    scalaVersion in ThisBuild := Versions.Scala,
    crossScalaVersions := Versions.ScalaCross
  )
}
object Publish {
  val defaultSettings = Seq(

    description := "Scala: Typelevel unboxed compile time dimensional analysis over tagged types. Intellij Idea compatible 100%",
    developers += Developer(
      "rudogma",
      "Mikhail Savinov",
      "mikhail@rudogma.org",
      url("https://github.com/Rudogma")
    ),

    publishTo in ThisBuild := Some(if (isSnapshot.value){
      Opts.resolver.sonatypeSnapshots
    }else{
      Opts.resolver.sonatypeStaging
    }),

    publishMavenStyle := true,

    publishArtifact in Test := false,

    pomIncludeRepository := { _ => false },

    //    releasePublishArtifactsAction := PgpKeys.publishSigned.value,

    licenses += ("MIT", url("https://opensource.org/licenses/MIT")),

    scmInfo := Some(
      ScmInfo(
        url("https://github.com/Rudogma/scala-superquants"),
        "scm:git:git@github.com:Rudogma/scala-superquants.git",
        Some("scm:git:ssh://github.com:Rudogma/scala-superquants.git")
      )
    )
  )
}

object Tests {
  val defaultSettings = Seq(
    libraryDependencies ++= Seq(
      "org.typelevel" %% "spire" % "0.14.1" % "test",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
    )
  )
}


object Console {
  val defaultSettings = Seq(
    scalacOptions ~= (_ filterNot (Set("-Xfatal-warnings", "-Ywarn-unused-import").contains)),

    initialCommands in console := """
     import superquants._
            superquants.render._""".stripMargin
  )
}
