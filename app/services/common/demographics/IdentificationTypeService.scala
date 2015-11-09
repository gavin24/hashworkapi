package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.IdentificationType
import repository.common.demographics.IdentificationTypeRepository
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
