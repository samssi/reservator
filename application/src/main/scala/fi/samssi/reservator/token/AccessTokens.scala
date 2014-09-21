package fi.samssi.reservator.token

import scala.collection.mutable.Map
import scala.util.Random

object AccessTokens {
  val tokens = Map[String, String]()

  def createToken(username: String) = {
    val token = TokenGenerator.generateNew();
    putToken(username, token)
    token
  }

  def putToken(username: String, token: String) = {
    tokens += (username -> token)
  }

  def isTokenValid(username: String, token: String) = {
    tokens.get(username) match {
      case Some(expected) => {
        if (token.equals(expected)) true
        else false
      }
      case None => false
    }
  }
}

object TokenGenerator {
  def generateNew() = {
    val chars = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM"
    val token = Seq.fill(50)(chars(Random.nextInt(chars.length))).mkString("")
    token
  }
}
