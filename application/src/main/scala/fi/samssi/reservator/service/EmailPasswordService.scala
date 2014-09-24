package fi.samssi.reservator.service

import javax.mail.{Transport, Message, Session}
import javax.mail.internet.{InternetAddress, MimeMessage}
import sun.net.smtp.SmtpClient
import fi.samssi.reservator.Logging
import java.util.Properties

class EmailPasswordService extends EmailService {
  def sendPasswordTo(address: String) {
    new SMTPClient().send(address)
  }
}
class SMTPClient extends Logging {

  def send(to: String) {
    val properties = new Properties()
    properties.put("mail.transport.protocol", "smtp")
    properties.put("mail.host", "smtp.kolumbus.fi")
    properties.put("mail.smtp.port", "25");
    //props.put("mail.smtp.starttls.enable", "true")g


    val mailSession = Session.getInstance(properties)
    val transport = mailSession.getTransport()

    val message = new MimeMessage(mailSession)
    message.setSubject("Testing new app stuff!")
    message.setContent("This is a test", "text/plain")
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to))

    transport.connect()
    transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO))
    transport.close()

    logger.info(s"Sent message to $to")
  }
}
