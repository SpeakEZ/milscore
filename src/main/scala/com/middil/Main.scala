package com.middil

import akka.actor.{Actor, Props, ActorSystem, ActorLogging }
import akka.io.IO
import spray.can.Http
import spray.http.{Uri, HttpMethods, HttpResponse, HttpRequest}


object Main extends App {
  implicit val system = ActorSystem()

  val handler = system.actorOf(Props[DemoService], name = "handler")
  IO(Http) ! Http.Bind(handler, "localhost", 8080)

}


class DemoService extends Actor with ActorLogging {
  def receive = {
    case _: Http.Connected =>
      log.info("Registering for new connection")
      sender ! Http.Register(self)

    case HttpRequest(HttpMethods.GET, Uri.Path("/ping"), _, _, _) =>
    sender ! HttpResponse(entity = "PONG!")

    case HttpRequest(HttpMethods.GET, Uri.Path("/pong"), _, _, _) =>
      sender ! HttpResponse(entity = "PING!")

    case _: HttpRequest => sender ! HttpResponse(status = 404,
      entity = "Unknown resource")

    case ev: Http.ConnectionClosed =>
      log.info(s"Conneciton closed due to [$ev]")
  }

}
