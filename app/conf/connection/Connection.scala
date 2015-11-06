package conf.connection

import com.typesafe.config.ConfigFactory
import com.websudos.phantom.connectors.{KeySpace, SimpleConnector, ContactPoints}
import com.websudos.phantom.dsl.Session
import scala.collection.JavaConversions._

/**
 * Created by hashcode on 2015/10/29.
 */

object Config {
  val config = ConfigFactory.load()

}

trait DefaultsConnector extends SimpleConnector {
  val hosts: Seq[String] = Config.config.getStringList("cassandra.host").toList
  implicit val keySpace: KeySpace = KeySpace(Config.config.getString("cassandra.keyspace"))
  override implicit lazy val session: Session = ContactPoints(hosts).keySpace(keySpace.name).session
}

object DataConnection extends DefaultsConnector {

}


