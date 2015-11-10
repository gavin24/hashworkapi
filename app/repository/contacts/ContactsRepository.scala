package repository.contacts

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.contacts.Contacts

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/10/31.
 */
//company: String,
//id: String,
//postalAddress: String,
//physicalAddress: String,
//contactNumber: String,
//postalCode: String,
//emailAddress: String
sealed class ContactsRepository extends CassandraTable[ContactsRepository,Contacts]{
  object company extends StringColumn(this) with PartitionKey[String]
  object id extends StringColumn(this) with PrimaryKey[String]
  object postalAddress extends MapColumn[ContactsRepository, Contacts, String, String](this)
  object physicalAddress extends MapColumn[ContactsRepository, Contacts, String, String](this)
  object contactNumber extends MapColumn[ContactsRepository, Contacts, String, String](this)
  object postalCode extends MapColumn[ContactsRepository, Contacts, String, String](this)
  object emailAddress extends MapColumn[ContactsRepository, Contacts, String, String](this)
  object state extends StringColumn(this)


  override def fromRow(r: Row): Contacts = {
    Contacts(
      company(r),
      id(r),
      postalAddress(r),
      physicalAddress(r),
      contactNumber(r),
      postalCode(r),
      emailAddress(r),state(r))

  }
}

object ContactsRepository extends ContactsRepository with RootConnector {
  override lazy val tableName = "contacts"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(contact: Contacts): Future[ResultSet] = {
    insert
      .value(_.id, contact.id)
      .value(_.company, contact.company)
      .value(_.contactNumber, contact.contactNumber)
      .value(_.emailAddress, contact.emailAddress)
      .value(_.physicalAddress, contact.physicalAddress)
      .value(_.postalAddress, contact.postalAddress)
      .value(_.postalCode, contact.postalCode)
      .future()
  }

  def findById(company:String, id: String):Future[Option[Contacts]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }
  def findAll(company:String): Future[Seq[Contacts]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }
}