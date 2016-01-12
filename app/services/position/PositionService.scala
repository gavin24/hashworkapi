package services.position

import com.websudos.phantom.dsl._
import domain.position.Position
import repository.position.PositionRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object PositionService extends Service {
  def createOrUpdate(position: Position): Future[ResultSet] = {
    PositionRepository.save(position)
  }

  def getPositionById(company: String, id: String): Future[Option[Position]] = {
    PositionRepository.getPositionById(company,id)
  }

  def getCompanyPositions(company: String): Future[Seq[Position]] = {
    PositionRepository.getCompanyPositions(company)
  }

}
