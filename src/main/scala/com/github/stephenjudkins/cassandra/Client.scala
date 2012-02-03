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
import java.nio.ByteBuffer
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

  implicit def bytesToByteBuffer(a: Array[Byte]) = ByteBuffer wrap a
}

import Client._

class Client(host: String, port: Int) {

  def clusterName:Future[String] = globalService.describe_cluster_name()
  def keyspaces:Future[Seq[Keyspace]] = globalService.describe_keyspaces() map { _.toSeq map { k => Keyspace(k.name) } }



  class KeyspaceClient(keyspace: String) {
    private val keyFactory = new KeyspacedFactory(keyspace, serviceFactory)
    private val service = new FactoryToService(keyFactory)

    def withColumnFamily(family: String) = new ColumnFamilyClient(family)

    class ColumnFamilyClient(family: String) {
      def columnParent = new ColumnParent(family)

      def get(key: Array[Byte], name: Array[Byte]) = {
        val path = new ColumnPath(family).tap(_.setColumn(name))

        service.get(key, path, ConsistencyLevel.QUORUM) handle {
          case t: Throwable => println(t); throw(t)
        } map {
          _.getColumn.getValue
        }
      }

      def insert(key: Array[Byte], name: Array[Byte], value: Array[Byte]) = {
        val column = (new Column).tap { c =>
          c.setName(name)
          c.setValue(value)
          c.setTimestamp(0L)
        }
        service.insert(key, columnParent, column, ConsistencyLevel.QUORUM)
      }

    }
  }


  def withKeyspace(keyspace:String) = new KeyspaceClient(keyspace)

  private def keyspaceDef(name: String, columnFamilies: Seq[String]) = new KsDef(name, "SimpleStrategy", columnFamilies map { c => new CfDef(name, c) }).tap { k =>
    k.setStrategy_options(Map("replication_factor" -> "1"))
  }

  def createKeyspace(name: String, columnFamilies: Seq[String]) = globalService.system_add_keyspace(keyspaceDef(name, columnFamilies))
  def dropKeyspace(name: String) = globalService.system_drop_keyspace(name)



  private val globalService:CassandraService = new FactoryToService(serviceFactory)

  private lazy val serviceFactory = ClientBuilder()               // 1
    .hosts(new InetSocketAddress(host, port))
    .codec(ThriftClientFramedCodec())
    .hostConnectionLimit(1)
    .buildFactory()
}