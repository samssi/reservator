package fi.samssi.notesongo.service

import org.json4s._
import org.json4s.jackson.JsonMethods._
import fi.samssi.notesongo.service.samssi.oshopper.repository.CentralRepository
import fi.samssi.notesongo.Logging
import com.mongodb.casbah.commons.MongoDBObject
import fi.samssi.notesongo.domain.User

class UserRepository extends CentralRepository with Logging {
    def repositoryCollection = "users"

    def getUser(username: String) = {
      val userSearchingObject = MongoDBObject("username" -> username)
      val result = fetchCollectionFromDb(repositoryCollection).findOne(userSearchingObject)
      parse(withFlattenedId(result.toString)).extract[User]
    }
  }

