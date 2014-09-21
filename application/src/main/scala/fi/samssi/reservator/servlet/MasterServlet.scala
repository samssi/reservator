package fi.samssi.reservator.servlet

import org.scalatra._
import org.scalatra.json.NativeJsonSupport
import org.json4s.DefaultFormats
import fi.samssi.reservator.token.AccessTokens

trait MasterServlet extends ScalatraServlet with NativeJsonSupport with CorsSupport {
  implicit override val jsonFormats = DefaultFormats

  def verifyToken(username: String, token: String) {
    if (!AccessTokens.isTokenValid(username, token)) {
      halt(401)
    }
  }

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
