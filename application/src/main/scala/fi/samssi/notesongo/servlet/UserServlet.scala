package fi.samssi.notesongo.servlet

import org.scalatra.{CorsSupport, Ok}
import com.google.inject.Inject
import fi.samssi.notesongo.service.UserRepository
import fi.samssi.notesongo.domain.{User, Result}
import fi.samssi.notesongo.token.AccessTokens

class UserServlet @Inject()(userRepository: UserRepository) extends MasterServlet {
  get("/user/:userId") {
    userRepository.getUser("user@user.com")
    Ok("success")
  }

  put("/user/login") {
    val user = parsedBody.extract[User]
    AccessTokens.createToken(user.username)
    Ok(Result("Success"))
  }
}
