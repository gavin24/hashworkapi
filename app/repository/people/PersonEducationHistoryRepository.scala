package repository.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.contacts.Contacts
import domain.people.Person
import repository.contacts.ContactsRepository._

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonEducationHistoryRepository extends CassandraTable[PersonEducationHistoryRepository, Person] {

  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object firstName extends StringColumn(this)

  object middleName extends StringColumn(this)

  object emailAddress extends StringColumn(this) with PartitionKey[String]

  object lastName extends StringColumn(this)

  object title extends StringColumn(this)

  object authvalue extends StringColumn(this)

  object enabled extends BooleanColumn(this)

  object accountNonExpired extends BooleanColumn(this)

  object credentialsNonExpired extends BooleanColumn(this)

  object accountNonLocked extends BooleanColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): Person = {
    Person(
      company(r),
      id(r),
      firstName(r),
      middleName(r),
      emailAddress(r),
      lastName(r),
      title(r),
      authvalue(r),
      enabled(r),
      accountNonExpired(r),
      credentialsNonExpired(r),
      accountNonLocked(r),
      state(r)
    )
  }
}

object PersonEducationHistoryRepository extends PersonEducationHistoryRepository with RootConnector {
  override lazy val tableName = "emailp"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(person: Person): Future[ResultSet] = {
    insert
      .value(_.company, person.company)
      .value(_.accountNonExpired, person.accountNonExpired)
      .value(_.authvalue, person.authvalue)
      .value(_.credentialsNonExpired, person.credentialsNonExpired)
      .value(_.accountNonLocked, person.accountNonLocked)
      .value(_.emailAddress, person.emailAddress)
      .value(_.enabled, person.enabled)
      .value(_.firstName, person.firstName)
      .value(_.id, person.id)
      .value(_.lastName, person.lastName)
      .value(_.middleName, person.middleName)
      .value(_.title, person.title)
      .value(_.state, person.state)
      .future()
  }

  def findById(company:String, id: String):Future[Option[Contacts]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }
  def findAll(company:String): Future[Seq[Contacts]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }


}

