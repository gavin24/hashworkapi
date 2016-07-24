package repository.people

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.PersonPosition

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
class PersonPositionRepository extends CassandraTable[PersonPositionRepository, PersonPosition] {


  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object statusDate extends DateColumn(this) with PrimaryKey[Date] with ClusteringOrder[Date] with Descending

  object positionId extends StringColumn(this)

  object statusId extends StringColumn(this)

  object reason extends StringColumn(this)

  override def fromRow(r: Row): PersonPosition = {
    PersonPosition(
      personId(r),
      id(r),
      statusDate(r),
      positionId(r),
      statusId(r),
      reason(r)
    )
  }
}

object PersonPositionRepository extends PersonPositionRepository with RootConnector {
  override lazy val tableName = "ppositions"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(position: PersonPosition): Future[ResultSet] = {
    insert
      .value(_.personId, position.personId)
      .value(_.id, position.id)
      .value(_.statusDate, position.statusDate)
      .value(_.positionId, position.positionId)
      .value(_.statusId, position.statusId)
      .value(_.reason, position.reason)
      .future()
  }

  def getPersonPosition(personId: String, id: String): Future[Option[PersonPosition]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def getPersonPositions(personId: String): Future[Seq[PersonPosition]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }

}
