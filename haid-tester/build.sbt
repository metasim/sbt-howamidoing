import eri.haid.HaidTestListener

name := "haid-tester"

version := "1.0.0-SNAPSHOT"

organization := "eri"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.1.3" % "test"
)

// testResultLogger in (Test, test) := TestResultLogger.Null

// testListeners in (Test, test) := Seq()

// fork := true

testOptions in (Test, test) ~= { options =>
    options.collect {
        case l: Tests.Listeners => l.copy(listeners = l.listeners.filter(_.isInstanceOf[HaidTestListener]))
        case o => o
    }
}


val root = project.in(file(".")).
    enablePlugins(eri.haid.SbtHowAmIDoing).
    disablePlugins(plugins.JUnitXmlReportPlugin)
