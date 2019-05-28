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

// Enable MySQL JDBC Datasource; note that "MySQL Connector/J 8.0 is highly
// recommended for use with MySQL Server 8.0, 5.7, 5.6, and 5.5":
//
// https://dev.mysql.com/downloads/connector/j/#current
//
// As of May 28, 2019, Delivery+ uses MySQL 5.6.26.

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.16"
