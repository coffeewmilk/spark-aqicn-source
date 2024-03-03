ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.custom"
ThisBuild / scalaVersion := "2.12.19"

lazy val root = (project in file("."))
  .settings(
    name := "spark-custom-source"
  )

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.5.0"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.0"

// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "3.5.0"


libraryDependencies += "com.lihaoyi" %% "requests" % "0.8.0"

libraryDependencies += "com.lihaoyi" %% "ujson" % "3.2.0"

libraryDependencies += "com.lihaoyi" %% "upickle" % "3.2.0"

assemblyMergeStrategy in assembly := {
 case PathList("META-INF", _*) => MergeStrategy.discard
 case _                        => MergeStrategy.first
}


