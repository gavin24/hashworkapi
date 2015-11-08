package services.company

import com.datastax.driver.core.ResultSet
import domain.company.{Department, Company}
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

}
