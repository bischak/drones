name := "drones"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.specs2" % "specs2-core_2.12" % "3.8.6" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

