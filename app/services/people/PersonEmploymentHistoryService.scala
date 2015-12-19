package services.people

import com.datastax.driver.core.ResultSet
import domain.people.PersonEmploymentHistory
import repository.people.PersonEmploymentHistoryRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
object PersonEmploymentHistoryService extends Service{
  def saveOrUpdate(entity: PersonEmploymentHistory): Future[ResultSet] = {
    PersonEmploymentHistoryRepository.save(entity)
  }

  def getValues(personId: String): Future[Seq[PersonEmploymentHistory]] = {
    PersonEmploymentHistoryRepository.findPersonEmploymentHistory(personId)
  }

  def getPersonValue(personId: String, id: String): Future[Option[PersonEmploymentHistory]] = {
    PersonEmploymentHistoryRepository.findById(personId, id)
  }

}
