package server

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletHolder, ServletContextHandler}
import com.sun.jersey.spi.container.servlet.ServletContainer
import org.eclipse.jetty.server.handler.HandlerList
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import api.PingResource
import javax.ws.rs.ext.ContextResolver
import collection.JavaConversions._

class JaxrsApplication extends javax.ws.rs.core.Application{

  val mapper = {
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper
  }

  override def getSingletons = Set(mapper, new JacksonContextResolver(mapper), new PingResource())
}

class JacksonContextResolver(mapper:ObjectMapper) extends ContextResolver[ObjectMapper] {
  def getContext(`type`: Class[_]) = mapper
}

object Runner extends App {
  val server = new Server(8080)

  val context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS)
  context.setContextPath("/")

  val jerseyServletHolder = new ServletHolder(classOf[ServletContainer])
  jerseyServletHolder.setInitParameter("javax.ws.rs.Application", classOf[JaxrsApplication].getName)
  jerseyServletHolder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true")
  context.addServlet(jerseyServletHolder, "/*")

  val handlers = new HandlerList()
  handlers.setHandlers(Array(context))
  server.setHandler(handlers)

  server.start()
}