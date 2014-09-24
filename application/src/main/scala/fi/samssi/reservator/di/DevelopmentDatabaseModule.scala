package fi.samssi.reservator.di

import com.google.inject.AbstractModule
import fi.samssi.reservator.service.{EmailPasswordService, EmailService}

class DevelopmentDatabaseModule extends AbstractModule {
  def configure() = {
    bind(classOf[EmailService]).to(classOf[EmailPasswordService])
  }
}
