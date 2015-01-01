package fi.samssi.reservator.servlet

import com.google.inject.Inject
import fi.samssi.reservator.service.ReservationsRepository
import org.scalatra.Ok

class ReservationsServlet @Inject()(reservationsRepository: ReservationsRepository) extends MasterServlet {
  // TODO: Use cookie
  get("/:username/:token") {
    verifyToken(params("username"), params("token"))
    val reservations = reservationsRepository.getReservations()
    Ok(reservations)
  }
}
