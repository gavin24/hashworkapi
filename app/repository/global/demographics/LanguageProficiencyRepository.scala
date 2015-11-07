package repository.global.demographics
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.global.demographics.LanguageProficiency
import views.html.helper
import views.html.helper.select

import scala.concurrent.Future

sealed class LanguageProficiencyRepository extends CassandraTable[LanguageProficiencyRepository,LanguageProficiency]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): LanguageProficiency = {
    LanguageProficiency(id(r),name(r))
  }
}

object LanguageProficiencyRepository extends LanguageProficiencyRepository with RootConnector {
  override lazy val tableName = "langpr"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(langpr: LanguageProficiency): Future[ResultSet] = {
    insert
      .value(_.id, langpr.id)
      .value(_.name, langpr.name)
      .future()
  }

  def findById(id: String):Future[Option[LanguageProficiency]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[LanguageProficiency]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
