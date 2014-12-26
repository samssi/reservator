package fi.samssi.reservator.di

import com.google.inject.AbstractModule
import fi.samssi.reservator.service.{MongoDbReservationsService, ReservationsService, EmailPasswordService, EmailService}

class DevelopmentDatabaseModule extends AbstractModule {
  def configure() = {
    bind(classOf[EmailService]).to(classOf[EmailPasswordService])
    bind(classOf[ReservationsService]).to(classOf[MongoDbReservationsService])
  }
}
