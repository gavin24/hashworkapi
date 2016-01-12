package services.position

import com.websudos.phantom.dsl._
import domain.position.PositionDesignation
import repository.position.PositionDesignationRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object PositionDesignationService extends Service {

  def createOrUpdate(designation: PositionDesignation): Future[ResultSet] = {
   PositionDesignationRepository.save(designation)
  }

  def getDesignationById(positionId: String, id: String): Future[Option[PositionDesignation]] = {
    PositionDesignationRepository.getDesignationById(positionId,id)
  }

  def getPositionDesignations(positionId: String): Future[Seq[PositionDesignation]] = {
    PositionDesignationRepository.getPositionDesignations(positionId)
  }

}
