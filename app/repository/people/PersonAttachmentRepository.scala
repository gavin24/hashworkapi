package repository.people

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
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

  object url extends StringColumn(this)

  object description extends StringColumn(this)

  object size extends OptionalStringColumn(this)

  object mime extends StringColumn(this)

  object date extends DateColumn(this)

  override def fromRow(r: Row): PersonAttachment = {
    PersonAttachment(
      company(r),
      id(r),
      url(r),
      size(r),
      mime(r),
      date(r))
  }
}

object PersonAttachmentRepository extends PersonAttachmentRepository with RootConnector {
  override lazy val tableName = "clogos"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(dept: PersonAttachment) = {
    insert
      .value(_.company, dept.company)
      .value(_.id, dept.id)
      .value(_.url, dept.url)
      .value(_.size, dept.size)
      .value(_.mime, dept.mime)
      .value(_.date, dept.date)
      .future()
  }

  def findDCompanyLogo(company: String, id: String): Future[Option[PersonAttachment]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

  def findCompanyLogos(company: String): Future[Seq[PersonAttachment]] = {
    select.where(_.company eqs company) fetchEnumerator() run Iteratee.collect()
  }
}
