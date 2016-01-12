package services.position

import com.websudos.phantom.dsl._
import domain.position.DepartureReason
import repository.position.DepartureReasonRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object DepartureReasonService extends Service {
  def createOrUpdate(reason: DepartureReason): Future[ResultSet] = {
    DepartureReasonRepository.save(reason)
  }

  def getDepartureReason(company: String, id: String): Future[Option[DepartureReason]] = {
    DepartureReasonRepository.getDepartureReason(company,id)
  }

  def getCompanyDepatureReasons(company: String): Future[Seq[DepartureReason]] = {
    DepartureReasonRepository.getCompanyDepatureReasons(company)
  }

}
