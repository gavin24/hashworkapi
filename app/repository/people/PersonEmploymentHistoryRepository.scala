package repository.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.people.PersonEmploymentHistory

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonEmploymentHistoryRepository extends CassandraTable[PersonEmploymentHistoryRepository, PersonEmploymentHistory] {


  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object companyName extends StringColumn(this)

  object companyAddress extends StringColumn(this)

  object companyTelephone extends StringColumn(this)

  object applicatSupervisor extends StringColumn(this)

  object contactSupervisor extends BooleanColumn(this)

  object reasonsForLeaving extends StringColumn(this)

  object startDate extends DateColumn(this)

  object endDate extends DateColumn(this)

  object startingSalary extends BigDecimalColumn(this)

  object endingSalary extends BigDecimalColumn(this)

  object currencyId extends StringColumn(this)

  object jobResponsibility extends StringColumn(this)

  object jobId extends StringColumn(this)

  object date extends DateColumn(this)

  object state extends StringColumn(this)



  override def fromRow(r: Row): PersonEmploymentHistory = {
    PersonEmploymentHistory(
      id(r),
      personId(r),
      companyName(r),
      companyAddress(r),
      companyTelephone(r),
      applicatSupervisor(r),
      contactSupervisor(r),
      reasonsForLeaving(r),
      startDate(r),
      endDate(r),
      startingSalary(r),
      endingSalary(r),
      currencyId(r),
      jobResponsibility(r),
      jobId(r),
      date(r),
      state(r)
    )
  }
}

object PersonEmploymentHistoryRepository extends PersonEmploymentHistoryRepository with RootConnector {
  override lazy val tableName = "pemp"


  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(person: PersonEmploymentHistory): Future[ResultSet] = {
    insert
      .value(_.personId, person.personId)
      .value(_.id, person.id)
      .value(_.companyName, person.companyName)
      .value(_.companyAddress, person.companyAddress)
      .value(_.companyTelephone, person.companyTelephone)
      .value(_.applicatSupervisor, person.applicatSupervisor)
      .value(_.contactSupervisor, person.contactSupervisor)
      .value(_.reasonsForLeaving, person.reasonsForLeaving)
      .value(_.startDate, person.startDate)
      .value(_.endDate, person.endDate)
      .value(_.startingSalary, person.startingSalary)
      .value(_.currencyId, person.currencyId)
      .value(_.jobResponsibility, person.jobResponsibility)
      .value(_.jobId, person.jobId)
      .value(_.date, person.date)
      .value(_.endingSalary, person.endingSalary)
      .value(_.state, person.state)
      .future()
  }

  def findById(personId: String, id: String): Future[Option[PersonEmploymentHistory]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def findPersonEmploymentHistory(personId: String): Future[Seq[PersonEmploymentHistory]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }


}
