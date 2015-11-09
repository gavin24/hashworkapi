package services.common.location

import com.datastax.driver.core.ResultSet
import domain.common.location.LocationType
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

}
