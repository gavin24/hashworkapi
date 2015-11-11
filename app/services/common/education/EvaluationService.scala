package services.common.education

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import domain.common.education.Evaluation
import repository.common.demographics.GenderRepository
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
  def get(id:String):Future[Option[Evaluation]] ={
    EvaluationRepository.findById(id)
  }

  def getAll:Future[Seq[Evaluation]] ={
    EvaluationRepository.findAll
  }
}
