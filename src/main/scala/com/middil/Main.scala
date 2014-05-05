package com.middil

import akka.actor.{Actor, Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import spray.http.{Uri, HttpMethods, HttpResponse, HttpRequest}

object Main extends App {
  implicit val system = ActorSystem()

  val handler = system.actorOf(Props[DemoService], name = "handler")
  IO(Http) ! Http.Bind(handler, "localhost", 8080)

}


class DemoService extends Actor {
  def receive = {
    case _: Http.Connected => sender ! Http.Register(self)

    case HttpRequest(HttpMethods.GET, Uri.Path("/ping"), _, _, _) =>
    sender ! HttpResponse(entity = "PONG!")

    case HttpRequest(HttpMethods.GET, Uri.Path("/pong"), _, _, _) =>
      sender ! HttpResponse(entity = "PING!")

    case _: HttpRequest => sender ! HttpResponse(status = 404,
      entity = "Unknown resource")
  }

}
