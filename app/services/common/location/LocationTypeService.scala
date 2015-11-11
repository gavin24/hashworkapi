package services.common.location

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import domain.common.location.LocationType
import repository.common.demographics.GenderRepository
import repository.common.location.LocationTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LocationTypeService extends Service{
  def saveOrUpdate(entity: LocationType): Future[ResultSet] = {
    LocationTypeRepository.save(entity)
  }
  def get(id:String):Future[Option[Gender]] ={
    GenderRepository.findById(id)
  }

  def getAll:Future[Seq[Gender]] ={
    GenderRepository.findAll
  }

}
