package services.people

import com.websudos.phantom.dsl._
import domain.people.PersonPosition
import repository.people.PersonPositionRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object PersonPositionService extends Service {
  def save(position: PersonPosition): Future[ResultSet] = {
    PersonPositionRepository.save(position)
  }

  def getPersonPosition(personId: String, id: String): Future[Option[PersonPosition]] = {
    PersonPositionRepository.getPersonPosition(personId, id)
  }

  def getPersonPositions(personId: String): Future[Seq[PersonPosition]] = {
    PersonPositionRepository.getPersonPositions(personId)
  }

}
