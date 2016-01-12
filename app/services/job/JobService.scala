package services.job

import com.websudos.phantom.dsl._
import domain.job.Job
import repository.job.JobRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object JobService extends Service {

  def createOrUpdate(job: Job): Future[ResultSet] = {
    JobRepository.save(job)
  }

  def getJobById(company: String, id: String): Future[Option[Job]] = {
    JobRepository.getJobById(company, id)
  }

  def getCompanyJobs(company: String): Future[Seq[Job]] = {
    JobRepository.getCompanyJobs(company)
  }

}
