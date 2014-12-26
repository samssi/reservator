package fi.samssi.reservator.token

import java.lang.String
import scala.Predef.String
import java.security.{SecureRandom, MessageDigest}
import fi.samssi.reservator.domain.User
import org.apache.commons.codec.binary.Hex

object CryptoUtil {
  def saltedUser(user: User) = {
    val username = user.username
    val salt = generateSalt()
    val hashedPassword = hashPassword(user.password, salt)
    new User(username, hashedPassword, salt)
  }

  def hashPassword(password: String, salt: String) = {
    new String(Hex.encodeHex(digest(password + salt)))
  }

  def digest(digestive: String) = {
    val digest = MessageDigest.getInstance("SHA-256")
    digest.digest(digestive.getBytes("UTF-8"))
  }

  def generateSalt() = {
    val random = new SecureRandom()
    val salt = new Array[Byte](64)
    random.nextBytes(salt)
    new String(Hex.encodeHex(salt))
  }

}
