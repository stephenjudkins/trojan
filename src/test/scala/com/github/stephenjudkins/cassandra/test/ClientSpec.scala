package com.github.stephenjudkins.cassandra.test

import org.specs2._
import com.github.stephenjudkins.cassandra.Client
import com.github.stephenjudkins.cassandra.Domain.Keyspace
import com.twitter.util.Future
import com.twitter.conversions.time._
import java.nio.charset.Charset

class ClientSpec extends Specification {

  def is = args(sequential = true) ^
    "get cluster name" ! getClusterName ^
    "describe keyspaces" ! describeKeyspaces ^
    "get and set" ! getAndSet

  def client = new Client("localhost", 9160)

  def getClusterName = client.clusterName() must_== "Test Cluster"


  def describeKeyspaces = {
    clearKeyspaces()
    client.createKeyspace("foo", Seq("cf1"))()
    client.keyspaces().toSet must_== Set(Keyspace("system"), Keyspace("foo"))
  }

  val UTF8 = Charset.forName("UTF-8")
  implicit def string2Bytes(s: String) = s.getBytes(UTF8)
  implicit def bytesToString(a: Array[Byte]) = new String(a, UTF8)

  def getAndSet = {
    clearKeyspaces()
    client.createKeyspace("test", Seq("cf1"))()
    val cfClient = client.withKeyspace("test").withColumnFamily("cf1")
    cfClient.insert("key", "name", "value")

    (cfClient.get("key", "name")():String) must_== "value"
  }

  def keyspacesToClear:Future[Seq[Keyspace]] = client.keyspaces map { _ filter { _.name != "system" }}

  def clearKeyspaces = keyspacesToClear flatMap { ks =>
    Future.join(ks map { k => client.dropKeyspace(k.name) })
  }

}