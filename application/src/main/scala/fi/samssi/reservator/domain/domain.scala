package fi.samssi.reservator.domain

import org.json4s.native.Serialization._
import fi.samssi.reservator.Json4sSettings

case class User(username: String, password: String, salt: Option[String]) extends JsonSerializable
case class AuthorizationToken(token: String)
case class PasswordRequest(email: String)

case class Reservation(id: String, reserver: String, startDate: String, endDate: String, note: String)
case class CalendarEvent(title: String, start: String, end: String)

trait JsonSerializable extends Json4sSettings {
  def asJson: String = write(this)
}