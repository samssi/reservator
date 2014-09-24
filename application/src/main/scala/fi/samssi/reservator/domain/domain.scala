package fi.samssi.reservator.domain

case class User(username: String, password: String)
case class PasswordRequest(email: String)
case class Reservation(id: String, reserver: String, startDate: String, endDate: String, note: String)
