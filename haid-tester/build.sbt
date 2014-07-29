name := "haid-tester"

version := "1.0.0-SNAPSHOT"

organization := "eri"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.1.3" % "test"
)

val root = project.in(file(".")).enablePlugins(eri.haid.SbtHowAmIDoing)
