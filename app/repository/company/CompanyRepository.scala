package repository.company

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.company.Company


/**
 * Created by hashcode on 2015/10/29.
 */


sealed class CompanyRepository extends CassandraTable[CompanyRepository, Company] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object details extends MapColumn[CompanyRepository, Company, String, String](this)

  override def fromRow(row: Row): Company = {
    Company(
      id(row),
      name(row),
      details(row)
    )
  }
}

object CompanyRepository extends CompanyRepository with RootConnector {
  override lazy val tableName = "company"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(company: Company) = {
    insert
      .value(_.id, company)
      .value(_.name, company.name)
      .value(_.details, company.details)
      .future()
  }

  def findById(id: String) = {
    select.where(_.id eqs id).one()
  }

}