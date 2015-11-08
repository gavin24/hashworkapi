package services.global.demographics

import com.datastax.driver.core.ResultSet
import domain.global.demographics.IdentificationType
import repository.global.demographics.IdentificationTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object IdentificationTypeService extends Service{

  def saveOrUpdate(entity: IdentificationType): Future[ResultSet] = {
    IdentificationTypeRepository.save(entity)
  }
}
