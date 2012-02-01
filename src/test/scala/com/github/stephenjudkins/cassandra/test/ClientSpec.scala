package com.github.stephenjudkins.cassandra.test

import org.specs2._
import com.github.stephenjudkins.cassandra.Client
import com.github.stephenjudkins.cassandra.Domain.Keyspace

class ClientSpec extends Specification {
  def is =
    "get cluster name" ! getClusterName ^
    "describe keyspaces" ! describeKeyspaces //^
//    "create keyspace, get, and set" ! createGetAndSet

  def client = new Client("localhost", 9160)

  def getClusterName = client.clusterName() must_== "Test Cluster"

  def describeKeyspaces = client.keyspaces() must_== Seq(Keyspace("system"))

//  def createGetAndSet = for {
//    created <- client.createKeyspace("keyspace", Seq("cf1"))
//    ks = client.withKeyspace("keyspace")
//    ks.set()
//  } yield {
//
//  }


}