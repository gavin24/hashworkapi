package services.job

import com.websudos.phantom.dsl._
import domain.job.JobEvent
import repository.job.JobEventRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object JobEventService extends Service {
  def createOrUpdate(jobEvent: JobEvent): Future[ResultSet] = {
    JobEventRepository.save(jobEvent)
  }

  def getJobEventById(jobId: String, id: String): Future[Option[JobEvent]] = {
    JobEventRepository.getJobEventById(jobId, id)
  }

  def getJobEvents(jobId: String): Future[Seq[JobEvent]] = {
    JobEventRepository.getJobEvents(jobId)
  }

}
