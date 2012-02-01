import sbt._
class Plugins(info: ProjectInfo) extends PluginDefinition(info)
{
  val mavenRepo = "Twitter" at "http://maven.twttr.com/"
  val sbtThrift = "com.twitter" % "sbt-thrift" % "2.0.2"
}
