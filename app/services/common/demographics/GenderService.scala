package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import repository.common.demographics.GenderRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object GenderService extends Service{

  def saveOrUpdate(entity: Gender): Future[ResultSet] = {
    GenderRepository.save(entity)
  }
}
