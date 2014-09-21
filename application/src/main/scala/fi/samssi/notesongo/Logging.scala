package fi.samssi.notesongo

import org.apache.log4j.Logger

trait Logging {
  val loggerName = this.getClass.getName
  lazy val logger = Logger.getLogger(loggerName)
}
