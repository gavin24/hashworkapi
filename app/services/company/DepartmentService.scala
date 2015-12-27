package services.company

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import domain.company.{Department, Company}
import repository.common.demographics.GenderRepository
import repository.company.{DepartmentRepository, CompanyRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object DepartmentService extends Service{

  def saveOrUpdate(entity: Department): Future[ResultSet] = {
    DepartmentRepository.save(entity)
  }
  def get(company:String, id:String):Future[Option[Department]] ={
    DepartmentRepository.findDepartment(company,id)
  }

  def getDepartments(company:String):Future[Seq[Department]] ={
    DepartmentRepository.findDepartments(company)
  }

}
