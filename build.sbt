name := "Latitude Financial Code Challenge"

organization := "net.allan"

version := "0.1"

scalaVersion := "2.12.6"
scalaBinaryVersion in ThisBuild := "2.12"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.6",
  "com.lihaoyi" %% "upickle" % "0.7.1",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint:_",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-unused-import",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
)

scalacOptions in (Compile, console) ~= (_.filterNot(Seq("-Xfatal-warnings", "-Ywarn-unused-import") contains _))

scalacOptions in (Test, console) := (scalacOptions in (Compile, console)).value

