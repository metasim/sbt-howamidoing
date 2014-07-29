package eri.haid

import sbt.Configurations.Test
import sbt.Keys._
import sbt.Project.inConfig
import sbt._


object SbtHowAmIDoing extends AutoPlugin {

    object autoImport {

        val haidStatsFile = settingKey[File]("Path to file for storing test results")
        val haidRecordResults = taskKey[File]("Record results from test task.")

         lazy val baseHaidSettings = Seq(
            haidStatsFile := baseDirectory.value / "sbt-haid.csv",
            haidRecordResults := {
                println("Haid record results: " + haidStatsFile.value)
                haidStatsFile.value
            },
            testListeners += new HaidTestListener(sLog.value)
        )
    }

    import eri.haid.SbtHowAmIDoing.autoImport._

    override def requires = sbt.plugins.JUnitXmlReportPlugin

    // This plugin is automatically enabled for projects which are JvmModules.
    override def trigger = allRequirements // or `noTrigger`

    override def projectSettings = inConfig(Test)(baseHaidSettings)

}

class HaidTestListener(log: Logger) extends TestsListener {
    override def doInit() {
        log.info("doInit")
    }

    override def doComplete(finalResult: TestResult.Value) {
        log.info("doComplete " + finalResult)
    }

    override def testEvent(event: TestEvent) {
        log.info("testEvent " + event)
    }

    override def startGroup(name: String) {
        log.info("startGroup name " + name)
    }

    override def endGroup(name: String, t: Throwable) {
        log.info("endGroup name " + name)
        log.info("endGroup t " + t)
    }

    override def endGroup(name: String, result: TestResult.Value) {
        log.info("endGroup name " + name)
        log.info("endGroup result " + result)

    }

}

