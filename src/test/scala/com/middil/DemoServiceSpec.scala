package com.middil

import org.scalatest._
import spray.testkit.ScalatestRouteTest
import org.scalatest.FreeSpec
import org.scalatest.Matchers

class DemoServiceSpec extends FreeSpec with ScalatestRouteTest with Matchers with DemoService {
  def actorRefFactory = system

  "The Demo Service" - {
    "when calling GET pong" - {
      "should return 'PING!'" in {
	Get("/pong") ~> route ~> check {
	  entity.toString should include("PING!")
	}
      }
    }
  }

}
