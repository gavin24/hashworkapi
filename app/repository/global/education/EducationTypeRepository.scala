import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection

import scala.concurrent.Future

sealed class EducationTypeRepository extends CassandraTable[EducationTypeRepository,EducationType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object description extends StringColumn(this)
  override def fromRow(r: Row): EducationType = {
    EducationType(id(r),name(r))
  }
}

object EducationTypeRepository extends EducationTypeRepository with RootConnector {
  override lazy val tableName = "edutype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(edutype: EducationType): Future[ResultSet] = {
    insert
      .value(_.id, edutype.id)
      .value(_.name, edutype.name)
      .future()
  }

  def findById(id: String):Future[Option[EducationType]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[EducationType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
