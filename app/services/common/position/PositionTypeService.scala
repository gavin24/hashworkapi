package services.common.position

import com.datastax.driver.core.ResultSet
import domain.common.position.PositionType
import repository.common.position.PositionTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object PositionTypeService extends Service {
  def saveOrUpdate(entity: PositionType): Future[ResultSet] = {
    PositionTypeRepository.save(entity)
  }

}
