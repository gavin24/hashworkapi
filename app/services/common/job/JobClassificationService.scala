package services.common.job

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import domain.common.job.JobClassification
import repository.common.demographics.GenderRepository
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
  def get(id:String):Future[Option[Gender]] ={
    GenderRepository.findById(id)
  }

  def getAll:Future[Seq[Gender]] ={
    GenderRepository.findAll
  }
}
