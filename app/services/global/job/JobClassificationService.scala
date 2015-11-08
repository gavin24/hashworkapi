package services.global.job

import com.datastax.driver.core.ResultSet
import domain.global.job.JobClassification
import repository.global.job.JobClassificationRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object JobClassificationService extends Service{

  def saveOrUpdate(entity: JobClassification): Future[ResultSet] = {
    JobClassificationRepository.save(entity)
  }
}
