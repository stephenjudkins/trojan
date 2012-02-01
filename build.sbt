unmanagedSourceDirectories in Compile <+= (sourceDirectory) { _ / "main/gen-java" }

resolvers ++= Seq(
  "Twitter" at "http://maven.twttr.com/"
)

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.twitter" %% "finagle-core" % "1.10.0",
  "com.twitter" %% "finagle-thrift" % "1.10.0" intransitive,
  "org.specs2" %% "specs2" % "1.6.1" % "test",
  "thrift" % "libthrift" % "0.5.0" from "http://maven.twttr.com/thrift/libthrift/0.5.0/libthrift-0.5.0.jar"
)

seq(cassandraSettings: _*)


