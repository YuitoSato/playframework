//
// Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
//

lazy val root = (project in file("."))
  .enablePlugins(PlayService)
  .enablePlugins(RoutesCompiler)
  .enablePlugins(MediatorWorkaroundPlugin)
  .settings(
    libraryDependencies ++= Seq(
      guice,
      component("play-akka-http-server")
    ),
    scalaVersion := sys.props.get("scala.version").getOrElse("2.12.4"),

    InputKey[Unit]("verifyResourceContains") := {
      val args = Def.spaceDelimited("<path> <status> <words> ...").parsed
      val path = args.head
      val status = args.tail.head.toInt
      val assertions = args.tail.tail
      DevModeBuild.verifyResourceContains(path, status, assertions, 0)
    }
  )