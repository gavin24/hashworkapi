package services.people

import com.datastax.driver.core.ResultSet
import domain.people.{PersonContinuingEducation, PersonAddress}
import repository.people.{PersonContinuingEducationRepository, PersonAddressRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
object PersonContinuingEducationService extends Service{
  def saveOrUpdate(entity: PersonContinuingEducation): Future[ResultSet] = {
    PersonContinuingEducationRepository.save(entity)
  }

  def getValues(personId: String): Future[Seq[PersonContinuingEducation]] = {
    PersonContinuingEducationRepository.findPersonContinuingEducation(personId)
  }

  def getPersonValue(personId: String, id: String): Future[Option[PersonContinuingEducation]] = {
    PersonContinuingEducationRepository.findById(personId, id)
  }

}
