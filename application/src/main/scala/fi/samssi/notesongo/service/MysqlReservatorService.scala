package fi.samssi.notesongo.service

import fi.samssi.notesongo.service.samssi.oshopper.repository.CentralRepository
import fi.samssi.notesongo.Logging
import com.mongodb.casbah.commons.MongoDBObject

class UserRepository extends CentralRepository with Logging {
    def repositoryCollection = "users"

    def getUser(username: String) = {
      val userSearchingObject = MongoDBObject("username" -> username)
      val result = fetchCollectionFromDb(repositoryCollection).findOne(userSearchingObject)

      println(withFlattenedId(result.toString))
    }
  }

