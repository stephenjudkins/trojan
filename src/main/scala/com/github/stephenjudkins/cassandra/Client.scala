package com.github.stephenjudkins.cassandra

import com.twitter.util.Future
import com.twitter.finagle.builder.ClientBuilder
import java.net.InetSocketAddress
import com.twitter.finagle.thrift.{ThriftClientFramedCodec, ThriftClientRequest}
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.cassandra.thrift.Cassandra.{ServiceIface, ServiceToClient}

import collection.JavaConversions._
import com.github.stephenjudkins.cassandra.Domain.Keyspace
import com.twitter.finagle._
import org.apache.cassandra.thrift._

object Client {
  type CassandraService = Service[ThriftClientRequest, Array[Byte]]
  type CassandraFactory = ServiceFactory[ThriftClientRequest, Array[Byte]]

  implicit def wrap(service: CassandraService):ServiceIface = new Cassandra.ServiceToClient(service, new TBinaryProtocol.Factory())

  class KeyspacedFactory(keyspace: String, underlying: CassandraFactory) extends ServiceFactoryProxy[ThriftClientRequest, Array[Byte]](underlying) {
    override def make() = for {
      client <- super.make()
      keyspaceSet <- client.set_keyspace(keyspace)
    } yield client
  }

  class Tappable[A](a: A) {
    def tap[B](f: A => B):A = {
      f(a)
      a
    }
  }

  implicit def toTappable[A](a: A) = new Tappable(a)
}

import Client._

class Client(host: String, port: Int) {

  def clusterName:Future[String] = globalService.describe_cluster_name()
  def keyspaces:Future[Seq[Keyspace]] = globalService.describe_keyspaces() map { _.toSeq map { k => Keyspace(k.name) } }

//  class

  class KeyspaceClient(keyspace: String) {

    private val keyService = new FactoryToService(keyFactory)
    private val keyFactory = new KeyspacedFactory(keyspace, serviceFactory)
    private val service = new FactoryToService(keyFactory)


  }
//
  def withKeyspace(keyspace:String) = new KeyspaceClient(keyspace)

  private def keyspaceDef(name: String, columnFamilies: Seq[String]) = new KsDef(name, "SimpleStrategy", columnFamilies map { c => new CfDef(name, c) }).tap { k =>
    k.strategy_options = Map("replication_factor" -> "1")
  }

  def createKeyspace(name: String, columnFamilies: Seq[String]) = globalService.system_add_keyspace(keyspaceDef(name, columnFamilies))



  private val globalService:CassandraService = new FactoryToService(serviceFactory)

  private lazy val serviceFactory = ClientBuilder()               // 1
    .hosts(new InetSocketAddress(host, port))
    .codec(ThriftClientFramedCodec())
    .hostConnectionLimit(1)
    .buildFactory()
}