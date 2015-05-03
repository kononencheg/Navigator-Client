enablePlugins(ScalaJSPlugin)

organization := "ru.livetex.navigator"
name := "navigator-client"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= {
  val scalajs = "0.8.0"

  Seq(
    "org.scala-js"  %% "scalajs-dom_sjs0.6"     % scalajs
  )
}
