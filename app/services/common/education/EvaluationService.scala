package services.common.education

import com.datastax.driver.core.ResultSet
import domain.common.education.Evaluation
import repository.common.education.EvaluationRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object EvaluationService extends Service{

  def saveOrUpdate(entity: Evaluation): Future[ResultSet] = {
    EvaluationRepository.save(entity)
  }
}
