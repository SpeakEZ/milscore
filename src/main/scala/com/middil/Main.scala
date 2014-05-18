package com.middil

import akka.actor.{ Actor, Props, ActorSystem, ActorLogging }
import akka.io.IO
import spray.can.Http
import spray.http.{ Uri, HttpMethods, HttpResponse, HttpRequest }
import spray.routing.HttpService
import spray.httpx.encoding.Gzip

object Main extends App {
  implicit val system = ActorSystem()

  val handler = system.actorOf(Props[SlickDemoServiceActor], name = "handler")
  IO(Http) ! Http.Bind(handler, "localhost", 8080)

}
