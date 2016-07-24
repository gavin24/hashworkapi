package repository.company

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.company.CompanyDocuments

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/05.
 */
class CompanyDocumentsRepository extends CassandraTable[CompanyDocumentsRepository, CompanyDocuments] {

  object company extends StringColumn(this) with PartitionKey[String]
  object id extends StringColumn(this) with PrimaryKey[String]
  object description extends StringColumn(this)
  object url extends StringColumn(this)
  object mime extends StringColumn(this)
  object date extends DateColumn(this)
  object permission extends SetColumn[String](this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): CompanyDocuments = {
    CompanyDocuments(
      company(r),
      id(r),
      description(r),
      url(r),
      mime(r),
      date(r),
      permission(r),
      state(r)
    )
  }
}
object CompanyDocumentsRepository extends CompanyDocumentsRepository with RootConnector {
  override lazy val tableName = "cdocs"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(doc: CompanyDocuments) = {
    insert
      .value(_.company, doc.company)
      .value(_.id, doc.id)
      .value(_.description, doc.description)
      .value(_.url, doc.url)
      .value(_.mime, doc.mime)
      .value(_.date, doc.date)
      .value(_.permission, doc.permission)
      .value(_.state, doc.state)
      .future()
  }

  def getCompanyDocumentById(company:String, id: String):Future[Option[CompanyDocuments]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }
  def getCompanyDocuments(company:String):Future[Seq[CompanyDocuments]] = {
    select.where(_.company eqs company)fetchEnumerator() run Iteratee.collect()
  }
}