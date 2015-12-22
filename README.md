A very simple [Scalding](https://github.com/twitter/scalding) job.  The most important thing to note is the setup of the
`build.sbt` file.

It has minimal dependencies:

```scala
libraryDependencies ++= Seq(
  "com.twitter" %% "scalding-core" % "0.15.0",
  "org.apache.hadoop" % "hadoop-client" % "2.2.0" % "provided",
  "org.slf4j" % "slf4j-log4j12" % "1.7.13" % "provided"
)
```

The `hadoop-client` jar is included for compilation, unit testing and running locally on your laptop but will not be pacakged
into the final "fat jar" assembly shipped out to the cluster.  Additionally, a logging framework is included so that we get
good logs when debugging locally.  A simple `log4j.properties` file is included in the repo.

The whole job can be run locally using real Hadoop libraries without any additional software install:

```shell
$ sbt 'run net.themodernlife.WordCount --hdfs --input build.sbt --output target/output'
```

It's even possible to get things like LZO-compression to work with this setup.  The main win here is that there is a very simple,
repeatable development environment that can be used to test all aspects of the job locally *before* sending things off to the cluster.