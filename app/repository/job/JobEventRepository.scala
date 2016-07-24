package repository.job

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.job.JobEvent

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */
//jobId:String, id:String,date:Date,event:String
class JobEventRepository extends CassandraTable[JobEventRepository, JobEvent] {


  object jobId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object date extends DateColumn(this) with PrimaryKey[Date] with ClusteringOrder[Date] with Descending

  object event extends StringColumn(this)


  override def fromRow(r: Row): JobEvent = {
    JobEvent(
      jobId(r),
      id(r),
      date(r),
      event(r)
    )
  }
}

object JobEventRepository extends JobEventRepository with RootConnector {
  override lazy val tableName = "jobevents"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(jobEvent: JobEvent): Future[ResultSet] = {
    insert
      .value(_.jobId, jobEvent.jobId)
      .value(_.id, jobEvent.id)
      .value(_.date, jobEvent.date)
      .value(_.event, jobEvent.event)
      .future()
  }

  def getJobEventById(jobId: String, id: String): Future[Option[JobEvent]] = {
    select.where(_.jobId eqs jobId).and(_.id eqs id).one()
  }

  def getJobEvents(jobId: String): Future[Seq[JobEvent]] = {
    select.where(_.jobId eqs jobId).fetchEnumerator() run Iteratee.collect()
  }

}
