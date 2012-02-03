import sbt._
import classpath.ClasspathUtilities
import Keys._

import org.apache.cassandra.thrift.CassandraDaemon
import org.apache.cassandra.config.DatabaseDescriptor

object CassandraBuild extends Build {

  val cassandraDaemon = AttributeKey[CassandraDaemon]("cassandra-daemon")

  lazy val project = Project (
    "finagle-cassandra",
    file (".")
  )


  val startCassandra = TaskKey[Unit]("start-cassandra", "start Cassandra Daemon")
  val stopCassandra = TaskKey[Unit]("stop-cassandra", "stop Cassandra Daemon")


  val cassandraSettings = Seq(
    onLoad in Global := { (state) => state.put(cassandraDaemon, new CassandraDaemon) },
    startCassandra <<= (state, baseDirectory) map { (state, dir) => try {
      val daemon = state.get(cassandraDaemon).get
      if (!daemon.isRPCServerRunning) {
        System.setProperty("cassandra.config", (dir / "conf" / "cassandra.yaml").toURI.toString)

        daemon.init(null)
        daemon.start()
      }
      } catch { case t: Throwable => t.printStackTrace() ; throw t}
    },
    stopCassandra <<= (state) map { state =>
      val daemon = state.get(cassandraDaemon).get
      daemon.stop()
    },
    test in Test <<= (test in Test).dependsOn(startCassandra)
  )


}
