package services.position

import com.websudos.phantom.dsl._
import domain.position.PositionEvent
import repository.position.PositionEventRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object PositionEventService extends Service {
  def save(event: PositionEvent): Future[ResultSet] = {
    PositionEventRepository.save(event)
  }

  def getPositionEvent(positionId: String, id: String): Future[Option[PositionEvent]] = {
    PositionEventRepository.getPositionEvent(positionId, id)
  }

  def getPositionEvents(positionId: String): Future[Seq[PositionEvent]] = {
    PositionEventRepository.getPositionEvents(positionId)
  }

}
