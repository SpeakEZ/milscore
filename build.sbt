organization  := "com.middil"

name := "milscore"

version       := "0.1"

scalaVersion  := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
    "spray repo" at "http://repo.spray.io/"
  )

libraryDependencies ++= {
  val sprayVersion = "1.2.1"
  val akkaVersion = "2.2.4"
  Seq(
    "io.spray" % "spray-can" % sprayVersion,
    "io.spray" % "spray-routing" % sprayVersion,
    "io.spray" % "spray-testkit" % sprayVersion % "test",
    "io.spray" % "spray-client" % sprayVersion,
    "io.spray" %%  "spray-json" % "1.2.6",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "ch.qos.logback" % "logback-classic" % "1.0.12",
    "org.scalatest" %% "scalatest" % "2.1.3" % "test",
//    "mysql" % "mysql-connector-java" % "5.1.21",
    "org.postgresql" % "postgresql" % "9.2-1004-jdbc4",
    "com.typesafe.slick" %% "slick" % "2.0.1",
    "org.slf4j" % "slf4j-nop" % "1.6.4", // Disable logging for Slick
    "com.typesafe" % "config" % "1.2.1"
  )
}


seq(Revolver.settings: _*)


scalariformSettings
