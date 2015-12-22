organization := "net.themodernlife"
name := "simple-scalding-example"

scalaVersion := "2.11.7"
scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-unchecked", "-feature")

resolvers ++= Seq(
  "Concurrent Maven Repo" at "http://conjars.org/repo",
  "Twitter Maven Repo" at "http://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "scalding-core" % "0.15.0" exclude("com.esotericsoftware.minlog", "minlog"),
  "org.apache.hadoop" % "hadoop-client" % "2.2.0" % "provided",
  "org.slf4j" % "slf4j-log4j12" % "1.7.13" % "provided"
)


fork in (Compile, run) := true
mainClass in (Compile, run) := Some("com.twitter.scalding.Tool")
outputStrategy in (Compile, run) := Some(StdoutOutput)
javaOptions in (Compile, run) ++= Seq(
  "-Dlog4j.configuration=file:%s".format(baseDirectory.value / "log4j.properties"),
  "-Dcascading.update.skip=true",
  "-Dmapreduce.local.map.tasks.maximum=4"
)

run <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

