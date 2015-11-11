package services.common.position

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import domain.common.position.PositionType
import repository.common.demographics.GenderRepository
import repository.common.position.PositionTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object PositionTypeService extends Service {
  def saveOrUpdate(entity: PositionType): Future[ResultSet] = {
    PositionTypeRepository.save(entity)
  }
  def get(id:String):Future[Option[Gender]] ={
    GenderRepository.findById(id)
  }

  def getAll:Future[Seq[Gender]] ={
    GenderRepository.findAll
  }

}
