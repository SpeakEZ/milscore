package com.middil

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}
import akka.io.IO
import spray.can.Http
import spray.http.{Uri, HttpMethods, HttpResponse, HttpRequest}
import spray.routing.HttpServiceActor
import spray.httpx.encoding.Gzip


object Main extends App {
  implicit val system = ActorSystem()

  val handler = system.actorOf(Props[DemoService], name = "handler")
  IO(Http) ! Http.Bind(handler, "localhost", 8080)

}

case class Color(red: Int, green: Int, blue: Int)  {
  require(red < 256, "RED must be >= 0 and <= 256")
  require(green < 256, "GREEN must be >= 0 and <= 256")
  require(blue < 256, "BLUE must be >= 0 and <= 256")
}

class DemoService extends HttpServiceActor {
  def receive = runRoute {
    get {
      path("color") {
	encodeResponse(Gzip) {
	  parameters('red.as[Int], 'green.as[Int], 'blue.as[Int] ? 50).as(Color) {
	    color =>
	      complete(page(color))
	  }
	}
      }
    } ~
      path("ping") {
	complete("PONG!")
      } ~
      path("pong") {
	complete("PING!")
      }
  }



  def page(c: Color) = {
    import c._
    def hex(i: Int) = "%02x" format i
    val color = '#' + hex(red) + hex(green) + hex(blue)

    <html>
    <body>
    <p>Color: {color}</p>
    <svg>
    <g transform="translate(100,100) rotate(200) scale(10)">
    <circle r="10" fill={color}></circle>
    <defs><circle id="eye" r="2.5" fill="white"></circle></defs>
    <use xlink:href="#eye" x="+3.5" y="+3"></use>
    <use xlink:href="#eye" x="-3.5" y="+3"></use>
    <path d="M -5, -3 A 6, 6 0 0 1 +5, -3" fill="none" stroke="white" stroke-width="1"></path>
    </g>
    </svg>
    </body>
    </html>
  }

}
