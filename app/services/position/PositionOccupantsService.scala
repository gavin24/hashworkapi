package services.position

import com.websudos.phantom.dsl._
import domain.position.PositionOccupants
import repository.position.PositionOccupantsRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object PositionOccupantsService extends Service {
  def save(occupants: PositionOccupants): Future[ResultSet] = {
    PositionOccupantsRepository.save(occupants)
  }

  def getPositionOccupant(positionId: String, id: String): Future[Option[PositionOccupants]] = {
    PositionOccupantsRepository.getPositionOccupant(positionId, id)
  }

  def getPositionOccupants(positionId: String): Future[Seq[PositionOccupants]] = {
    PositionOccupantsRepository.getPositionOccupants(positionId)
  }

}
