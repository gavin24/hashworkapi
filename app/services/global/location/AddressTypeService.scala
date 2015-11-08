package services.global.location

import com.datastax.driver.core.ResultSet
import domain.global.location.AddressType
import repository.global.location.AddressTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object AddressTypeService extends Service{
  def saveOrUpdate(entity: AddressType): Future[ResultSet] = {
    AddressTypeRepository.save(entity)
  }

}
