package services.position

import com.websudos.phantom.dsl._
import domain.position.DepartureReason
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object DepartureReasonService extends Service {
  def save(reason: DepartureReason): Future[ResultSet] = {
    DepartureReasonService.save(reason)
  }

  def getDepartureReason(company: String, id: String): Future[Option[DepartureReason]] = {
    DepartureReasonService.getDepartureReason(company,id)
  }

  def getCompanyDepatureReasons(company: String): Future[Seq[DepartureReason]] = {
    DepartureReasonService.getCompanyDepatureReasons(company)
  }

}
