name := """play-web-service"""
organization := "com.windriver"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice

fork := true // required for "sbt run" to pick up javaOptions

javaOptions += "-Dplay.editor=http://localhost:63342/api/file/?file=%s&line=%s"
