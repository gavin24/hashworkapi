package services.common.job

import com.datastax.driver.core.ResultSet
import domain.common.job.JobClassification
import repository.common.job.JobClassificationRepository
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
