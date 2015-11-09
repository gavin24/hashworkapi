package services.common.location

import com.datastax.driver.core.ResultSet
import domain.common.location.AddressType
import repository.common.location.AddressTypeRepository
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
