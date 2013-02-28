package api

import javax.ws.rs.{Produces, GET, Path}
import javax.ws.rs.core.MediaType

case class PingResponse(message:String)

@Path("/ping")
@Produces(Array(MediaType.APPLICATION_JSON))
class PingResource {

  @GET
  def ping = PingResponse("pong")

}
