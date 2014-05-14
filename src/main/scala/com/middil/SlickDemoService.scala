package com.middil

import akka.actor.Actor
import spray.routing.HttpService

class SlickDemoServiceActor extends Actor with DemoService {
  def actorRefFactory = context
  def receive = runRoute(route)
}

trait SlickDemoService extends HttpService {
  def route = {
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
