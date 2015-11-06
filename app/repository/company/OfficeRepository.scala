package repository.company

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import conf.connection.DataConnection
import domain.company.Office

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
sealed class OfficeRepository extends CassandraTable[OfficeRepository,Office]{
  override def fromRow(r: Row): Office = ???
}

object OfficeRepository extends OfficeRepository with RootConnector {
  override lazy val tableName = "office"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
}

