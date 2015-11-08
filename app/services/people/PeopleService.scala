package services.people

import com.datastax.driver.core.ResultSet
import domain.people.Person
import repository.people.PersonRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object PeopleService extends Service{

  def saveOrUpdate(entity: Person): Future[ResultSet] = {
    PersonRepository.save(entity)
  }
}
