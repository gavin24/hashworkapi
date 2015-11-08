package services.global.demographics

import com.datastax.driver.core.ResultSet
import domain.global.demographics.Role
import repository.global.demographics.RoleRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object RoleService extends Service{

  def saveOrUpdate(entity: Role): Future[ResultSet] = {
    RoleRepository.save(entity)
  }
}
