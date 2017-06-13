scalaVersion := "2.12.0"

//enablePlugins(ScalaJSPlugin)

libraryDependencies ++= Seq(
  "org.lwjgl" % "lwjgl" % "3.0.0b",
  "org.scalaz" %% "scalaz-core" % "7.2.7",
  "org.scala-lang.modules" %% "scala-swing" % "2.0.0-M2",
  //"org.scala-js" %%% "scalajs-dom" % "0.8.1",
  //"be.doeraene" %%% "scalajs-jquery" % "0.8.0",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.l33tlabs.twl" % "pngdecoder" % "1.0")
  //"com.lihaoyi" %%% "utest" % "0.3.0" % "test")
libraryDependencies += "com.github.kenbot" %%  "goggles-dsl"     % "1.0"
 
libraryDependencies += "com.github.kenbot" %%  "goggles-macros"  % "1.0"

scalacOptions += "-Yrangepos" // Enables better error messages

//jsDependencies += RuntimeDOM
//skip in packageJSDependencies := false
//testFrameworks += new TestFramework("utest.runner.Framework")
//persistLauncher in Compile := true
//persistLauncher in Test := false

scalacOptions += "-Yrangepos"

javaOptions += "-XstartOnFirstThread"

fork := true

fork in Test := false

initialCommands := """
  import gamesketch._;
"""
