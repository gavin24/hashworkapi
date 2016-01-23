package services.payroll.salary

import java.util.UUID

import com.websudos.phantom.dsl._
import conf.util.Util
import domain.payroll.salary.{Notch, Grade}
import repository.payroll.salary.GradeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/10.
 */
object GradeService extends Service {
  def createOrUpdate(grade: Grade): Future[ResultSet] = {

   GradeRepository.save(grade)
  }

  def getGradeById(company: String, id: String): Future[Option[Grade]] = {
    GradeRepository.getGradeById(company,id)
  }

  def getCompanyGrades(company: String): Future[Seq[Grade]] = {
    GradeRepository.getCompanyGrades(company)
  }

  def createNotches(gradeId:String, topAmount:BigDecimal, lowerAmount:BigDecimal)={
    Notch(gradeId,Util.md5Hash(UUID.randomUUID().toString),"name",BigDecimal("23"))
  }

}
