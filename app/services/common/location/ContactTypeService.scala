package services.common.location

import com.datastax.driver.core.ResultSet
import domain.common.location.ContactType
import repository.common.location.ContactTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object ContactTypeService extends Service {

  def saveOrUpdate(entity: ContactType): Future[ResultSet] = {
    ContactTypeRepository.save(entity)
  }
}
