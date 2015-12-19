package services.people

import com.datastax.driver.core.ResultSet
import domain.people.{PersonAddress, PersonEducationHistory}
import repository.people.PersonEducationHistoryRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
object PersonEducationHistoryService extends Service{
  def saveOrUpdate(entity: PersonEducationHistory): Future[ResultSet] = {
    PersonEducationHistoryRepository.save(entity)
  }

  def getValues(personId: String): Future[Seq[PersonEducationHistory]] = {
    PersonEducationHistoryRepository.findPersonEducationHistory(personId)
  }

  def getPersonValue(personId: String, id: String): Future[Option[PersonEducationHistory]] = {
    PersonEducationHistoryRepository.findById(personId, id)
  }

}
