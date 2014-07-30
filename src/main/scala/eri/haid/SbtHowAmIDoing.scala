package eri.haid

import sbt.Configurations.Test
import sbt.Keys._
import sbt.Project.inConfig
import sbt._
import sbt.testing.{Logger ⇒ TLogger}

object SbtHowAmIDoing extends AutoPlugin {

    object autoImport {
        val haidStatsFile = settingKey[File]("Path to file for storing test results")
        val haidReportResults = taskKey[File]("Record results from test task.")

         lazy val baseHaidSettings = Seq(
            haidStatsFile := baseDirectory.value / "sbt-haid.csv",
            haidReportResults <<= (test, haidStatsFile, sLog) map { (_, haidFile, log) ⇒
                log.info("Haid recorded results in : " + haidFile)
                haidFile
            },
            testListeners :=  {
                val haidFile = haidStatsFile.value
                val log = TestLogger.wrap(sLog.value)
                Seq(new HaidTestListener(haidFile, log))
            }
        )
    }

    import eri.haid.SbtHowAmIDoing.autoImport._

    override def requires = sbt.plugins.JvmPlugin
    
    // This plugin is automatically enabled for projects which are JvmModules.
    override def trigger = allRequirements // or `noTrigger`

    override def projectSettings = inConfig(Test)(baseHaidSettings)
}

private[haid] case class HaidStats(totalGroups: Int, passGroups: Int, failGroups: Int)

class HaidTestListener(haidFile: File, log: TLogger) extends TestsListener {

    var stats = HaidStats(0, 0, 0)

    override def doInit() {
        log.info("doInit")
    }

    override def doComplete(finalResult: TestResult.Value) {
        log.info("************* doComplete " + finalResult)
    }

    override def testEvent(event: TestEvent) {
        log.info("testEvent " + event)
    }

    override def startGroup(name: String) {
        log.info("startGroup name " + name)
        stats = stats.copy(totalGroups = stats.totalGroups + 1)
    }

    override def endGroup(name: String, t: Throwable) {
        log.info("endGroup name " + name)
        log.info("endGroup t " + t)
        stats = stats.copy(failGroups = stats.failGroups + 1)
    }

    override def endGroup(name: String, result: TestResult.Value) {
        log.info("endGroup name " + name)
        log.info("endGroup result " + result)
        stats = stats.copy(passGroups = stats.passGroups + 1)
    }

//    override def contentLogger(test: TestDefinition): Option[ContentLogger] = {
//        val noop = () ⇒ { () }
//        log.info("contentLogger test " + test)
//        Option(new ContentLogger(log, noop))
//    }
}

