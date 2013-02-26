name := "Bdd Jaxrs Scala Hands on"

version := "0.1"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-servlet" % "8.1.0.RC5",
  "javax.servlet" % "servlet-api" % "2.5",
  "com.sun.jersey" % "jersey-core" % "1.17",
  "com.sun.jersey" % "jersey-server" % "1.17",
  "com.sun.jersey" % "jersey-servlet" % "1.17",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.1.3",
  "com.fasterxml.jackson.jaxrs" % "jackson-jaxrs-json-provider" % "2.1.2",
  "org.scalatest" % "scalatest_2.10.0" % "2.0.M5" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test"
  )

seq(cucumberSettings : _*)

seq(Revolver.settings: _*) //Restart the server whenever code changes
