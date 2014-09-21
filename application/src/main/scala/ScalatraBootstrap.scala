import com.google.inject.{Guice, Injector}
import fi.samssi.notesongo.di.DevelopmentDatabaseModule
import fi.samssi.notesongo.servlet.UserServlet
import javax.servlet.ServletContext
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) = {
    val injector = createInjector()
    context.mount(injector.getInstance(classOf[UserServlet]), "/")
  }

  private def createInjector(): Injector = {
    Guice.createInjector(new DevelopmentDatabaseModule())
  }
}
