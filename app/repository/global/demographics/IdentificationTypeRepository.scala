package repository.global.demographics

/**
 * Created by hashcode on 2015/11/07.
 */
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.global.demographics.IdentificationType

import scala.concurrent.Future

sealed class IdentificationTypeRepository extends CassandraTable[IdentificationTypeRepository,IdentificationType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): IdentificationType = {
    IdentificationType(id(r),name(r))
  }
}

object IdentificationTypeRepository extends IdentificationTypeRepository with RootConnector {
  override lazy val tableName = "idtypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(idtype: IdentificationType): Future[ResultSet] = {
    insert
      .value(_.id, idtype.id)
      .value(_.name, idtype.name)
      .future()
  }

  def findById(id: String):Future[Option[IdentificationType]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[IdentificationType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
