import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection

import scala.concurrent.Future

sealed class ContactTypeRepository extends CassandraTable[ContactTypeRepository,ContactType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): ContactType = {
    ContactType(id(r),name(r))
  }
}

object ContactTypeRepository extends ContactTypeRepository with RootConnector {
  override lazy val tableName = "conacttypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(ContactType: ContactType): Future[ResultSet] = {
    insert
      .value(_.id, ContactType)
      .value(_.name, ContactType.name)
      .future()
  }

  def findById(id: String):Future[Option[ContactType]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[ContactType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}