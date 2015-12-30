package repository.storage

import com.datastax.driver.core.Row
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.storage.CompanyFiles

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/29.
 */


sealed class CompanyFilesRepository extends CassandraTable[CompanyFilesRepository, CompanyFiles] {

  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object file extends BlobColumn(this)

  object filename extends StringColumn(this)

  object mimetype extends StringColumn(this)

  override def fromRow(r: Row): CompanyFiles = {
    CompanyFiles(
      company(r),
      id(r),
      file(r),
      filename(r),
      mimetype(r)
    )
  }
}

object CompanyFilesRepository extends CompanyFilesRepository with RootConnector {
  override lazy val tableName = "cfiles"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(file: CompanyFiles): Future[ResultSet] = {
    insert
      .value(_.company, file.company)
      .value(_.id, file.id)
      .value(_.file, file.file)
      .value(_.filename, file.filename)
      .value(_.mimetype, file.mimetype)
      .future()
  }

  def findCompanyFiles(company: String): Future[Seq[CompanyFiles]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }

  def findFileById(company: String, id: String): Future[Option[CompanyFiles]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

}
