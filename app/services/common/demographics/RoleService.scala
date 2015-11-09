package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Role
import repository.common.demographics.RoleRepository
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
