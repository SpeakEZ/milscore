package com.middil

import akka.actor.ActorSystem
import akka.io.IO
import spray.can.Http
import spray.http.{HttpResponse, HttpRequest}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

object Main extends App {
  implicit val timeout: Timeout = 2.seconds
  implicit val system = ActorSystem()
  import system.dispatcher

  val future = IO(Http) ? HttpRequest(uri = "http://spray.io")

  future.mapTo[HttpResponse] onComplete {response =>
    println(s"The response is $response")
    system.shutdown()
  }

}
