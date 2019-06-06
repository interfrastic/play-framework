name := """ebeandemo"""

version := "1.0-SNAPSHOT"

// Enable Ebean ORM.

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

// Enable MySQL JDBC Datasource
//
// Version 5.1.18 is chosen to match the versions in use by Delivery+ as of
// May 28, 2019; see project/plugins.sbt for more information.

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.18"
