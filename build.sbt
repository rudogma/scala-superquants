//organization := "org.rudogma"
//name := "superquants"
//
//
//scalaVersion := "2.12.2"
//
//isSnapshot := true
//
//scalacOptions in ThisBuild ++= Seq(
//  "-deprecation",
//  "-encoding", "UTF-8",
//  "-feature",
//  "-unchecked",
//  "-Xfatal-warnings",
//  "-Yno-adapted-args",
//  "-Ywarn-numeric-widen"
////  "-Ywarn-value-discard"
//  ,"-opt:l:project",
//  "-opt-warnings",
//  "-explaintypes"
//
////  "-Ywarn-unused-import"
//)
//
//
//libraryDependencies ++= Seq(
//
//  "org.rudogma" %% "superquants" % "1.2-SNAPSHOT",
//  "com.chuusai" %% "shapeless" % "2.3.2",
//
//  "org.typelevel" %% "spire" % "0.14.1" % "test",
//  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
//  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
//)


import sbt._
import sbtcrossproject.{crossProject, CrossType}

releaseCrossBuild := true
publishMavenStyle := true
pomIncludeRepository := (_ => false)
releasePublishArtifactsAction := PgpKeys.publishSigned.value

lazy val defaultSettings =
  Project.defaultSettings ++
    Compiler.defaultSettings ++
    Publish.defaultSettings ++
    Tests.defaultSettings ++
    Console.defaultSettings

lazy val superquants = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .in(file("."))
  .settings(defaultSettings: _*)
  .jsSettings(
    crossScalaVersions := Versions.ScalaCross,
    parallelExecution in Test := false
  )

lazy val root = project.in(file("."))
  .settings(defaultSettings: _*)
  .settings(
    name := "superquants",
    publish := {},
    publishLocal := {},
    publishArtifact := false
  )
  .aggregate(superquantsJVM, superquantsJS)

lazy val superquantsJVM = superquants.jvm
lazy val superquantsJS = superquants.js
//lazy val superquantsNative = superquants.native