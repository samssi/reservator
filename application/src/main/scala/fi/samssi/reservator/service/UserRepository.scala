package fi.samssi.reservator.service

import org.json4s._
import org.json4s.jackson.JsonMethods._
import fi.samssi.reservator.service.samssi.oshopper.repository.CentralRepository
import fi.samssi.reservator.Logging
import com.mongodb.casbah.commons.MongoDBObject
import fi.samssi.reservator.domain.{Reservation, User}

class UserRepository extends CentralRepository with Logging {
    def repositoryCollection = "users"

    def getUser(username: String) = {
      val userSearchingObject = MongoDBObject("username" -> username)
      val result = fetchCollectionFromDb(repositoryCollection).findOne(userSearchingObject)
      parse(withFlattenedId(result.toString)).extract[User]
    }
  }

class ReservationsRepository extends CentralRepository with Logging {
  def repositoryCollection = "reservations"

  def getReservations() = {
    val result = fetchCollectionFromDb(repositoryCollection).find()
    parse(withFlattenedId(result.toArray.toString)).extract[List[Reservation]]
  }
}

