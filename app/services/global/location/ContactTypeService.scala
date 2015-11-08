package services.global.location

import com.datastax.driver.core.ResultSet
import domain.global.location.ContactType
import repository.global.location.ContactTypeRepository
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
