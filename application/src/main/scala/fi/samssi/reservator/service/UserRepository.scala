package fi.samssi.reservator.service

import org.json4s._
import org.json4s.jackson.JsonMethods._
import fi.samssi.reservator.service.samssi.oshopper.repository.CentralRepository
import fi.samssi.reservator.Logging
import com.mongodb.casbah.commons.MongoDBObject
import fi.samssi.reservator.domain.{Reservation, User}
import fi.samssi.reservator.domain.Reservation
import scala.Some

class UserRepository extends CentralRepository with Logging {
    def repositoryCollection = "users"

    def getUser(username: String) = {
      val userSearchingObject = MongoDBObject("username" -> username)
      val result = Option(fetchCollectionFromDb(repositoryCollection).findOne(userSearchingObject))
      result match {
        case Some(users) => Some(parse(withFlattenedId(users.toString)).extract[User])
        case None => None
      }

    }
  }

class ReservationsRepository extends CentralRepository with Logging {
  def repositoryCollection = "reservations"

  def getReservations() = {
    val result = fetchCollectionFromDb(repositoryCollection).find()
    parse(withFlattenedId(result.toArray.toString)).extract[List[Reservation]]
  }
}

