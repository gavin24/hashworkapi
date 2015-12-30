package repository.storage

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.storage.CompanyImages

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/29.
 */
sealed class CompanyImagesRepository extends CassandraTable[CompanyImagesRepository, CompanyImages] {

  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object image extends BlobColumn(this)

  object filename extends StringColumn(this)

  object mimetype extends StringColumn(this)

  override def fromRow(r: Row): CompanyImages = {
    CompanyImages(
      company(r),
      id(r),
      image(r),
      filename(r),
      mimetype(r)
    )
  }
}

object CompanyImagesRepository extends CompanyImagesRepository with RootConnector {
  override lazy val tableName = "cimages"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(image: CompanyImages): Future[ResultSet] = {
    insert
      .value(_.company, image.company)
      .value(_.id, image.id)
      .value(_.image, image.image)
      .value(_.filename, image.filename)
      .value(_.mimetype, image.mimetype)
      .future()
  }

  def findCompanyImages(company: String): Future[Seq[CompanyImages]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }

  def findImageById(company: String, id: String): Future[Option[CompanyImages]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

}
