package services.people

import com.datastax.driver.core.ResultSet
import domain.people.PersonIdentity
import repository.people.PersonIdentityRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
object PersonIdentityService extends Service{
  def saveOrUpdate(entity: PersonIdentity): Future[ResultSet] = {
    PersonIdentityRepository.save(entity)
  }

  def getValues(personId: String): Future[Seq[PersonIdentity]] = {
    PersonIdentityRepository.findPersonIdentities(personId)
  }

  def getPersonValue(personId: String, id: String): Future[Option[PersonIdentity]] = {
    PersonIdentityRepository.findById(personId, id)
  }

}
