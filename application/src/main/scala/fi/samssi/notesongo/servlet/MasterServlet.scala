package fi.samssi.notesongo.servlet

import org.scalatra._
import org.scalatra.json.NativeJsonSupport
import org.json4s.DefaultFormats

trait MasterServlet extends ScalatraServlet with NativeJsonSupport with CorsSupport {
  implicit override val jsonFormats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  error {
    case e => {
      e.printStackTrace
      InternalServerError("Query failed")
    }
  }
}
