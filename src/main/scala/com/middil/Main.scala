package com.middil

import akka.actor.ActorSystem
import akka.io.IO
import spray.can.Http
import spray.http.HttpRequest
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

object Main extends App {
  implicit val system = ActorSystem()

  IO(Http) ! HttpRequest(uri = "http://scala.io")

  system.shutdown()

}
