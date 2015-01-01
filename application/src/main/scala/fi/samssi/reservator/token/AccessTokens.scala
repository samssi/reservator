package fi.samssi.reservator.token

import scala.collection.mutable.Map
import scala.util.Random

object AccessTokens {
  val tokens = Map[String, String]()

  def createToken(username: String) = {
    val token = CryptoUtil.generateSalt()
    putToken(username, token)
    token
  }

  def revokeToken(username: String) = {
    tokens.remove(username);
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
