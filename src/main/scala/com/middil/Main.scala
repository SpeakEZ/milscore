package com.middil

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}
import akka.io.IO
import spray.can.Http
import spray.http.{Uri, HttpMethods, HttpResponse, HttpRequest}
import spray.routing.HttpServiceActor


object Main extends App {
  implicit val system = ActorSystem()

  val handler = system.actorOf(Props[DemoService], name = "handler")
  IO(Http) ! Http.Bind(handler, "localhost", 8080)

}


class DemoService extends HttpServiceActor {
  def receive = runRoute {
    get {
      path("ping") {
	complete("PONG!")
      } ~
      path("pong") {
	complete("PING!")
      }
    }
  }
}
