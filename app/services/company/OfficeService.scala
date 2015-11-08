package services.company

import com.datastax.driver.core.ResultSet
import domain.company.{Office, Company}
import repository.company.{OfficeRepository, CompanyRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object OfficeService extends Service{

  def saveOrUpdate(entity: Office): Future[ResultSet] = {
    OfficeRepository.save(entity)
  }

}
