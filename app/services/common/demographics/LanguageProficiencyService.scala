package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.{Gender, LanguageProficiency}
import repository.common.demographics.{GenderRepository, LanguageProficiencyRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LanguageProficiencyService extends Service{

  def saveOrUpdate(entity: LanguageProficiency): Future[ResultSet] = {
    LanguageProficiencyRepository.save(entity)
  }
  def get(id:String):Future[Option[LanguageProficiency]] ={
    LanguageProficiencyRepository.findById(id)
  }

  def getAll:Future[Seq[LanguageProficiency]] ={
    LanguageProficiencyRepository.findAll
  }
}
