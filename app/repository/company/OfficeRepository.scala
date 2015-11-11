package repository.company

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.company.{Department, Office}

/**
 * Created by hashcode on 2015/10/31.
 */
//company:String,
//id: String,
//name: String,
//description: String,
//active: String,
//officeTypeId: String,
//contactId: String
sealed class OfficeRepository extends CassandraTable[OfficeRepository, Office] {

  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object name extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object description extends StringColumn(this)

  object active extends BooleanColumn(this)

  object officeTypeId extends StringColumn(this)

  object contactId extends StringColumn(this)
  object state extends StringColumn(this)

  override def fromRow(r: Row): Office = {
    Office(
    company(r),id(r),name(r),description(r),active(r),officeTypeId(r),contactId(r),state(r)
    )
  }
}

object OfficeRepository extends OfficeRepository with RootConnector {
  override lazy val tableName = "office"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(office: Office) = {
    insert
      .value(_.company, office.company)
      .value(_.id, office.id)
      .value(_.name, office.name)
      .value(_.description, office.description)
      .value(_.active, office.active)
      .value(_.officeTypeId, office.officeTypeId)
      .value(_.contactId, office.contactId)
      .future()
  }

  def findById(company:String,id: String) = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

  def findOffices(company:String) = {
    select.where(_.company eqs company)fetchEnumerator() run Iteratee.collect()

  }

}

