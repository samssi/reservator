package fi.samssi.notesongo.servlet

import org.scalatra.{Unauthorized, CorsSupport, Ok}
import com.google.inject.Inject
import fi.samssi.notesongo.service.UserRepository
import fi.samssi.notesongo.domain.{User, Result}
import fi.samssi.notesongo.token.AccessTokens

class UserServlet @Inject()(userRepository: UserRepository) extends MasterServlet {
  get("/user") {
    Ok("success")
  }

  post("/user/login") {
    val user = parsedBody.extract[User]
    val expected = userRepository.getUser("user@user.com")
    if (user.equals(expected)) {
      AccessTokens.createToken(user.username)
      Ok(Result("Success"))
    }
    else {
      Unauthorized(Result("Not a valid username or password"))
    }
  }
}
