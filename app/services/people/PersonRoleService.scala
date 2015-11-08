package services.people

import com.datastax.driver.core.ResultSet
import domain.people.PersonRole
import repository.people.PersonRoleRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object PersonRoleService extends Service {
  def saveOrUpdate(entity: PersonRole): Future[ResultSet] = {
    PersonRoleRepository.save(entity)
  }

}
