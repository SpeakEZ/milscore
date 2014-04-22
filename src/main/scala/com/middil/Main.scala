package com.middil

import akka.actor.ActorSystem

object Main extends App {

  val system = ActorSystem()

  system.shutdown()

}
