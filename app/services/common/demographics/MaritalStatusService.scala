package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.MaritalStatus
import repository.common.demographics.MaritalStatusRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object MaritalStatusService extends Service{
  def saveOrUpdate(entity: MaritalStatus): Future[ResultSet] = {
    MaritalStatusRepository.save(entity)
  }

}
