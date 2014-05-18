package com.middil

import com.typesafe.config.ConfigFactory
//import slick.driver.MySQLDriver.simple._
import slick.driver.PostgresDriver.simple._

object Connector {

  val config = ConfigFactory.load()

  // Left for reference
  // val url = "jdbc:mysql://" + config.getString("db.host") + ":" + config.getString("db.port") + "/" + config.getString("db.database")
  // val driver = "com.mysql.jdbc.Driver"

  val url = "jdbc:postgresql://" + config.getString("db.host") + ":" + config.getString("db.port") + "/" + config.getString("db.database")
  val driver = "org.postgresql.Driver"

  val database = Database.forURL(url, driver = driver, user = config.getString("db.user"), password = config.getString("db.password"))
}

trait ConnectorService {

  val db = Connector.database

}
