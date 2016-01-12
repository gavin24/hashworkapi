package services.position

import com.websudos.phantom.dsl._
import domain.position.PositionPackage
import repository.position.PositionPackageRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object PositionPackageService extends Service {
  def createOrUpdate(pospackage: PositionPackage): Future[ResultSet] = {
    PositionPackageRepository.save(pospackage)
  }

  def getPositionPackage(positionId: String, id: String): Future[Option[PositionPackage]] = {
    PositionPackageRepository.getPositionPackage(positionId,id)
  }

  def getPositionPackages(positionId: String): Future[Seq[PositionPackage]] = {
    PositionPackageRepository.getPositionPackages(positionId)
  }

}
