package repository.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.people.PersonContinuingEducation

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonContinuingEducationRepository extends CassandraTable[PersonContinuingEducationRepository, PersonContinuingEducation] {

  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object courseId extends StringColumn(this)

  object competencyEvaluationId extends StringColumn(this)

  object courseScheduleId extends StringColumn(this)

  object date extends DateColumn(this)

  object state extends StringColumn(this)


  override def fromRow(r: Row): PersonContinuingEducation = {
    PersonContinuingEducation(
      id(r),
      personId(r),
      courseId(r),
      competencyEvaluationId(r),
      courseScheduleId(r),
      date(r),
      state(r)
    )
  }
}

object PersonContinuingEducationRepository extends PersonContinuingEducationRepository with RootConnector {
  override lazy val tableName = "pced"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(ceducation: PersonContinuingEducation): Future[ResultSet] = {
    insert
      .value(_.personId, ceducation.personId)
      .value(_.id, ceducation.id)
      .value(_.courseId, ceducation.courseId)
      .value(_.competencyEvaluationId, ceducation.competencyEvaluationId)
      .value(_.courseScheduleId, ceducation.courseScheduleId)
      .value(_.date, ceducation.date)
      .value(_.state, ceducation.state)

      .future()
  }

  def findById(personId: String, id: String): Future[Option[PersonContinuingEducation]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def findPersonContinuingEducation(personId: String): Future[Seq[PersonContinuingEducation]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }


}
