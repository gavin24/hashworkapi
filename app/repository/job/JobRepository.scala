package repository.job

import java.util.Date

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.job.Job

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/09.
 */

class JobRepository extends CassandraTable[JobRepository, Job] {

  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object jobClassificationId extends StringColumn(this)

  object title extends StringColumn(this)

  object code extends StringColumn(this)

  object description extends StringColumn(this)

  object date extends DateColumn(this) with PrimaryKey[Date] with ClusteringOrder[Date] with Descending

  object state extends StringColumn(this)


  override def fromRow(r: Row): Job = {
    Job(
      company(r),
      id(r),
      jobClassificationId(r),
      title(r),
      code(r),
      description(r),
      date(r),
      state(r)
    )
  }
}

object JobRepository extends JobRepository with RootConnector {
  override lazy val tableName = "jobs"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(job: Job): Future[ResultSet] = {
    insert
      .value(_.company, job.company)
      .value(_.id, job.id)
      .value(_.jobClassificationId, job.jobClassificationId)
      .value(_.title, job.title)
      .value(_.code, job.code)
      .value(_.description, job.description)
      .value(_.date, job.date)
      .value(_.state, job.state)
      .future()
  }

  def getJobById(company: String, id: String): Future[Option[Job]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

  def getCompanyJobs(company: String): Future[Seq[Job]] = {
    select.where(_.company eqs company).fetchEnumerator() run Iteratee.collect()
  }

}
