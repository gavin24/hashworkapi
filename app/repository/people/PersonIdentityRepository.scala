package repository.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.people.PersonIdentity

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonIdentityRepository extends CassandraTable[PersonIdentityRepository, PersonIdentity] {

  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object idType extends StringColumn(this)

  object idValue extends StringColumn(this)

  object preffered extends BooleanColumn(this)

  object date extends DateColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonIdentity = {
    PersonIdentity(
      id(r),
      personId(r),
      idType(r),
      idValue(r),
      preffered(r),
      date(r),
      state(r)
    )
  }
}

object PersonIdentityRepository extends PersonIdentityRepository with RootConnector {
  override lazy val tableName = "pids"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personIdentity: PersonIdentity): Future[ResultSet] = {
    insert
      .value(_.personId, personIdentity.personId)
      .value(_.id, personIdentity.id)
      .value(_.idType, personIdentity.idType)
      .value(_.idValue, personIdentity.idValue)
      .value(_.preffered, personIdentity.preffered)
      .value(_.date, personIdentity.date)
      .value(_.state, personIdentity.state)
      .future()
  }

  def findById(personId: String, id: String): Future[Option[PersonIdentity]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def findPersonIdentities(personId: String): Future[Seq[PersonIdentity]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }
}
