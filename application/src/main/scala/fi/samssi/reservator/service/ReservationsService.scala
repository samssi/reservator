package fi.samssi.reservator.service

import com.google.inject.Inject
import fi.samssi.reservator.domain.Reservation

trait ReservationsService {
  def getReservations()
}

class MongoDbReservationsService @Inject()(reservationsRepository: ReservationsRepository) extends ReservationsService {
  def getReservations()  = {
    val reservations = reservationsRepository.getReservations()

  }
  private def mapAsCalendarEvents(reservations: List[Reservation]) = {
    //reservations.map(reservation => )
    None
  }
}
