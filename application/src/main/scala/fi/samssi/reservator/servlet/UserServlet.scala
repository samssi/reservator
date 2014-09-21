package fi.samssi.reservator.servlet

import org.scalatra.{Unauthorized, Ok}
import com.google.inject.Inject
import fi.samssi.reservator.service.UserRepository
import fi.samssi.reservator.domain.{User, Result}
import fi.samssi.reservator.token.AccessTokens

class UserServlet @Inject()(userRepository: UserRepository) extends MasterServlet {
  post("/login") {
    val user = parsedBody.extract[User]
    val expected = userRepository.getUser("user@user.com")
    if (user.equals(expected)) {
      Ok(AccessTokens.createToken(user.username))
    }
    else {
      Unauthorized(Result("Not a valid username or password"))
    }
  }
}
