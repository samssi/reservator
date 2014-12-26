package fi.samssi.reservator.servlet

import org.scalatra.{CorsSupport, Unauthorized, Ok}
import com.google.inject.Inject
import fi.samssi.reservator.service.{EmailService, UserService}
import fi.samssi.reservator.domain.{PasswordRequest, User, Result}
import fi.samssi.reservator.token.AccessTokens
import fi.samssi.reservator.Logging

class UserServlet @Inject()(userRepository: UserService, emailService: EmailService) extends MasterServlet with Logging {
  post("/login") {
    val input = parsedBody.extract[User]
    val expected = userRepository.getUser(input.username)
    expected match {
      case Some(user) => checkPassword(input, user)
      case None => faultyLogin()
    }

  }

  post("/login/create") {
    val input = parsedBody.extract[User]
    //user
  }

  private def checkPassword(input: User, expected: User) {
    if (input.password.equals(expected)) {
      logger.info(s"User ${input.username} logged in.")
      Ok(AccessTokens.createToken(input.username))
    }
    else {
      faultyLogin()
    }
  }

  private def faultyLogin() {
    logger.info("Faulty logging attempt!")
    Unauthorized(Result("Not a valid username or password"))
  }

  post("/password/request") {
    val passwordRequest = parsedBody.extract[PasswordRequest]
    val user = userRepository.getUser(passwordRequest.email)
    user match {
      case Some(user) => {
        logger.info(s"Emailing new password to user: ${user.username}")
        emailService.sendPasswordTo(user.username)
      }
      case None => logger.error(s"Password attempt request to unknown email: ${passwordRequest.email}")
    }
  }

}
