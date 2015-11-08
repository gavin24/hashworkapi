package services.global.demographics

import com.datastax.driver.core.ResultSet
import domain.global.demographics.Race
import repository.global.demographics.RaceRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object RaceService extends Service{

  def saveOrUpdate(entity: Race): Future[ResultSet] = {
    RaceRepository.save(entity)
  }
}
