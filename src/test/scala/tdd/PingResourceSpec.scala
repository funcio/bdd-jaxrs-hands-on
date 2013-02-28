package tdd

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import api.{PingResponse, PingResource}

class PingResourceSpec extends FlatSpec with ShouldMatchers {

  behavior of "Ping resource"

  it should "reply with pong" in {
      new PingResource().ping should be (PingResponse("pong"))
  }

}
