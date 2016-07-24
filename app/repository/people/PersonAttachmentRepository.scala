package repository.people

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.people.PersonAttachment

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
class PersonAttachmentRepository extends CassandraTable[PersonAttachmentRepository, PersonAttachment] {


  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object personId extends StringColumn(this) with PrimaryKey[String]

  object url extends StringColumn(this)

  object description extends StringColumn(this)
  
  object mime extends StringColumn(this)

  object date extends DateColumn(this)

  override def fromRow(r: Row): PersonAttachment = {
    PersonAttachment(
      company(r),
      personId(r),
      id(r),
      url(r),
      description(r),
      mime(r),
      date(r))
  }
}

object PersonAttachmentRepository extends PersonAttachmentRepository with RootConnector {
  override lazy val tableName = "pattachments"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(attachment: PersonAttachment) = {
    insert
      .value(_.company, attachment.company)
      .value(_.personId, attachment.personId)
      .value(_.id, attachment.id)
      .value(_.url, attachment.url)
      .value(_.description, attachment.description)
      .value(_.mime, attachment.mime)
      .value(_.date, attachment.date)
      .future()
  }

  def getAttachment(company: String, personId:String,id: String): Future[Option[PersonAttachment]] = {
    select.where(_.company eqs company).and(_.personId eqs personId).and(_.id eqs id).one()
  }

  def getPersonAttachments(company: String,personId:String): Future[Seq[PersonAttachment]] = {
    select.where(_.company eqs company).and(_.personId eqs personId) fetchEnumerator() run Iteratee.collect()
  }

  def getCompanyPeopleAttachments(company: String): Future[Seq[PersonAttachment]] = {
    select.where(_.company eqs company) fetchEnumerator() run Iteratee.collect()
  }
}

