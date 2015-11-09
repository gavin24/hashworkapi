package services.common.util

import com.datastax.driver.core.ResultSet
import domain.common.util.Status
import repository.common.util.StatusRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object  StatusService extends Service{
  def saveOrUpdate(entity: Status): Future[ResultSet] = {
    StatusRepository.save(entity)
  }

}
