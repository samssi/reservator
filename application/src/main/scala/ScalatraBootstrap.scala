import com.google.inject.{Guice, Injector}
import fi.samssi.reservator.di.DevelopmentDatabaseModule
import fi.samssi.reservator.servlet.{ReservationsServlet, UserServlet}
import javax.servlet.ServletContext
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {
  private val apiUrl = "/reservator/v1"

  override def init(context: ServletContext) = {
    val injector = createInjector()
    context.mount(injector.getInstance(classOf[UserServlet]), prefixed("/user"))
    context.mount(injector.getInstance(classOf[ReservationsServlet]), prefixed("/reservations"))
  }

  private def createInjector(): Injector = {
    Guice.createInjector(new DevelopmentDatabaseModule())
  }

  private def prefixed(url: String) = {
    s"$apiUrl$url"
  }
}
