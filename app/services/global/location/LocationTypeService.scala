package services.global.location

import com.datastax.driver.core.ResultSet
import domain.global.location.LocationType
import repository.global.location.LocationTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LocationTypeService extends Service{
  def saveOrUpdate(entity: LocationType): Future[ResultSet] = {
    LocationTypeRepository.save(entity)
  }

}
