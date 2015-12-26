package repository.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.people.PersonEducationHistory

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonEducationHistoryRepository extends CassandraTable[PersonEducationHistoryRepository, PersonEducationHistory] {

  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object institutionName extends StringColumn(this)

  object institutionLocation extends StringColumn(this)

  object yearOfGraduation extends IntColumn(this)

  object educationTypeId extends StringColumn(this)

  object degreeId extends StringColumn(this)

  object notes extends StringColumn(this)

  object date extends DateColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonEducationHistory = {
    PersonEducationHistory(
      id(r),
      personId(r),
      institutionLocation(r),
      institutionLocation(r),
      yearOfGraduation(r),
      educationTypeId(r),
      degreeId(r),
      notes(r),
      date(r),
      state(r)
    )
  }
}

object PersonEducationHistoryRepository extends PersonEducationHistoryRepository with RootConnector {
  override lazy val tableName = "pedu"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(person: PersonEducationHistory): Future[ResultSet] = {
    insert
      .value(_.personId, person.personId)
      .value(_.id, person.id)
      .value(_.institutionLocation, person.institutionLocation)
      .value(_.institutionName, person.institutionName)
      .value(_.yearOfGraduation, person.yearOfGraduation)
      .value(_.educationTypeId, person.educationTypeId)
      .value(_.degreeId, person.degreeId)
      .value(_.notes, person.notes)
      .value(_.date, person.date)
      .value(_.state, person.state)
      .future()
  }

  def findById(personId: String, id: String): Future[Option[PersonEducationHistory]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def findPersonEducationHistory(personId: String): Future[Seq[PersonEducationHistory]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }

}

