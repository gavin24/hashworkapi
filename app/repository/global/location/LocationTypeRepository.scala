package repository.global.location
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.global.location.LocationType

import scala.concurrent.Future

sealed class LocationTypeRepository extends CassandraTable[LocationTypeRepository,LocationType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object code extends StringColumn(this)
  override def fromRow(r: Row): LocationType = {
    LocationType(id(r),name(r),code(r))
  }
}

object LocationTypeRepository extends LocationTypeRepository with RootConnector {
  override lazy val tableName = "locationtypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(locationtypes: LocationType): Future[ResultSet] = {
    insert
      .value(_.id, locationtypes.id)
      .value(_.name, locationtypes.name)
      .value(_.code, locationtypes.code)
      .future()
  }

  def findById(id: String):Future[Option[LocationType]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[LocationType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
