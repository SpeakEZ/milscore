package com.middil

import akka.actor.Actor
import spray.routing.HttpService

import slick.driver.PostgresDriver.simple._

class SlickDemoServiceActor extends Actor with SlickDemoService {
  def actorRefFactory = context
  def receive = runRoute(route)
}

class Activities(tag: Tag) extends Table[(Int, String)](tag, "activities") {
  def id = column[Int]("id", O.PrimaryKey)
  def graded_by = column[String]("graded_by")
  def * = (id, graded_by)
}

trait SlickDemoService extends HttpService {
  def route = {
    get {
      path("ping") {
        val foo = Courses.getActivity(15)
        complete(s"PONG! $foo")
      } ~
        path("pong") {
          complete("PING!")
        }
    }
  }
}

object Courses extends ConnectorService {

  val activities = TableQuery[Activities]

  def getActivity(id: Int) = db withSession {
    implicit session =>

      val activity = activities.filter(_.id === id)
      activity.first._2
  }

}
