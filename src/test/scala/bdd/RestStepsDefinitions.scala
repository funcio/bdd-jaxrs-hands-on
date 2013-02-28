package bdd

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.matchers.ShouldMatchers
import cucumber.runtime.PendingException

class RestStepsDefinitions extends ScalaDsl with EN with ShouldMatchers {

  var lastResponse:Response = Response(200, Map(), "")

  When("""^I (GET|POST|PUT|DELETE) (.+)$"""){ (method:String, uri:String ) =>
   lastResponse = Http.send(method, uri)
  }

  Then("""^the response status is (\d+)$"""){ (expectedStatus:Int) =>
    lastResponse.status should be(expectedStatus)
  }

  Then("""^I receive a JSON message$"""){ (entity:String) =>
     lastResponse.body should be(entity)
  }
}
