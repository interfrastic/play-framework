// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.9")

// Web plugins
addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.4")
addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.8")
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.0")
addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.6")

// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.1.0")

// Play Ebean support, to enable, uncomment this line, and enable in your build.sbt using
// enablePlugins(PlayEbean).

// Version 3.0.0 is chosen to match the versions in use by Delivery+ as of May
// 28, 2019:
//
// Ebean ORM              6.18.1
// MySQL Connector/J      5.1.18
// Play Framework         2.5.9
// SBT Play Ebean Plugin  3.0.0
//
// This table shows the relationship among the Ebean Plugin, Play, and Ebean
// versions:
//
// https://github.com/playframework/play-ebean#releases
//
// Here are notes on MySQL Connector/J version compatibility:
//
// https://dev.mysql.com/downloads/connector/j/#current

addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "3.0.0")
