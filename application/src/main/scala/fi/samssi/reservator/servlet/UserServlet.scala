package fi.samssi.reservator.servlet

import org.scalatra.{CorsSupport, Unauthorized, Ok}
import com.google.inject.Inject
import fi.samssi.reservator.service.UserRepository
import fi.samssi.reservator.domain.{PasswordRequest, User, Result}
import fi.samssi.reservator.token.AccessTokens
import fi.samssi.reservator.Logging

class UserServlet @Inject()(userRepository: UserRepository) extends MasterServlet with Logging {
  post("/login") {
    val user = parsedBody.extract[User]
    val expected = userRepository.getUser(user.username)
    if (user.equals(expected)) {
      logger.info(s"User ${user.username} logged in.")
      Ok(AccessTokens.createToken(user.username))
    }
    else {
      logger.info("Faulty logging attempt!")
      Unauthorized(Result("Not a valid username or password"))
    }
  }

  post("/password/request") {
    val passwordRequest = parsedBody.extract[PasswordRequest]
    val user = userRepository.getUser(passwordRequest.email)
    user match {
      case Some(user) => logger.info(s"Emailing new password to user: ${user.username}")
      case None => logger.error(s"Password attempt request to unknown email: ${passwordRequest.email}")
    }
  }

}
