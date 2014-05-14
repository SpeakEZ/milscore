package com.middil

import org.scalatest._
import spray.testkit.ScalatestRouteTest
import org.scalatest.FreeSpec
import org.scalatest.Matchers
import spray.httpx.encoding.Gzip
import spray.httpx.unmarshalling._
import spray.http._
import HttpHeaders._
import HttpEncodings._


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

    "when calling GET  /color?red=64&green=192&blue=164" - {
      "should return hex value #40c0a4" in {
	Get("/color?red=64&green=192&blue=164") ~> route ~> check {
	  header[`Content-Encoding`] === Some(`Content-Encoding`(gzip))
	  Gzip.decode(response).entity.as[String].right.get should include("#40c0a4")
	}
      }
    }

  }



}
