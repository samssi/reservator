package fi.samssi.notesongo

object PropertiesUtil {
  def getMongoUri: String = {
    sys.props.get("mongo.uri") match {
      case Some(x) => x
      case None => throw new RuntimeException("Please set -Dmongo.uri=xxx parameter to connect to the database!")
    }
  }
}
