package repository.company

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.company.Department

/**
 * Created by hashcode on 2015/10/31.
 */

//company:String,
//id: String,
//name: String,
//description: String,
//active: Boolean
sealed class DepartmentRepository extends CassandraTable[DepartmentRepository, Department] {

  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object description extends StringColumn(this)

  object active extends BooleanColumn(this)

  override def fromRow(r: Row): Department = {
    Department(company(r),
      id(r),
      name(r),
      description(r),
      active(r))
  }
}

object DepartmentRepository extends DepartmentRepository with RootConnector {
  override lazy val tableName = "dept"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

}
