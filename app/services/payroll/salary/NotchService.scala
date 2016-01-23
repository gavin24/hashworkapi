package services.payroll.salary

import com.websudos.phantom.dsl._
import domain.payroll.salary.Notch
import repository.payroll.salary.NotchRepository
import services.Service

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/01/23.
  */
object NotchService extends Service {
  def createOrUpdate(notch: Notch): Future[ResultSet] = {
    NotchRepository.save(notch)
  }

  def getNotchById(gradeId: String, id: String): Future[Option[Notch]] = {
    NotchRepository.getNotchById(gradeId, id)
  }

  def getGradeNotches(gradeId: String): Future[Seq[Notch]] = {
    NotchRepository.getGradeNotches(gradeId)
  }

}