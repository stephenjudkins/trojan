import sbt._
import com.twitter.sbt._
class CodeGenProject(info: ProjectInfo) extends DefaultProject(info) with CompileThriftFinagle
{
  // override def installActions = "update" :: "run" :: Nil
  val mavenRepo = "Twitter" at "http://maven.twttr.com/"

  def finagleVersion = "1.10.0"
  val finagleCore = "com.twitter" %% "finagle-core" % finagleVersion
  val finagleThrift = "com.twitter" %% "finagle-thrift" % finagleVersion
  val finagleOstrich = "com.twitter" %% "finagle-ostrich4" % finagleVersion
  val slf4j = "org.slf4j" % "slf4j-nop" % "1.6.4"

}
