package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.{Gender, IdentificationType}
import repository.common.demographics.{GenderRepository, IdentificationTypeRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object IdentificationTypeService extends Service{

  def saveOrUpdate(entity: IdentificationType): Future[ResultSet] = {
    IdentificationTypeRepository.save(entity)
  }
  def get(id:String):Future[Option[Gender]] ={
    GenderRepository.findById(id)
  }

  def getAll:Future[Seq[Gender]] ={
    GenderRepository.findAll
  }
}
