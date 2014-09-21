package fi.samssi.notesongo.service

package samssi.oshopper.repository

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoClientURI
import com.mongodb.util.JSON._
import org.bson.types.ObjectId
import org.json4s.native.JsonMethods.{parse => json4sParse}
import org.json4s.native.Serialization._
import com.mongodb.util.JSON.parse
import fi.samssi.notesongo.{Json4sSettings, PropertiesUtil}

trait CentralRepository extends Json4sSettings {
  lazy val mongoUri = PropertiesUtil.getMongoUri
  lazy val centralClient = MongoClient(MongoClientURI(mongoUri))

  def repositoryCollection: String
  def fetchCollectionFromDb(collection: String) = centralClient("reservator").getCollection(collection)
  def insert(json: String) { fetchCollectionFromDb(repositoryCollection).insert(parse(json).asInstanceOf[DBObject]) }
  def select(id: String) = fetchCollectionFromDb(repositoryCollection).findOne(ObjectId.massageToObjectId(id)).toString
  def delete(id: String) = fetchCollectionFromDb(repositoryCollection).remove(DBObject("_id" -> ObjectId.massageToObjectId(id)))
  def withFlattenedId(json: String) = {
    val result = json4sParse(json).transformField {
      case("_id", x) => ("id", x \ "$oid")
    }
    write(result)
  }
}

