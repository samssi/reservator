package fi.samssi.reservator.service

trait EmailService {
  def sendPasswordTo(address: String)
}
