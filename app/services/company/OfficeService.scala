package services.company

import com.datastax.driver.core.ResultSet
import domain.company.Office
import repository.company.OfficeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object OfficeService extends Service{

  def saveOrUpdate(entity: Office): Future[ResultSet] = {
    OfficeRepository.save(entity)
  }
  def getOffice(company:String, id:String):Future[Option[Office]] ={
    OfficeRepository.findById(company,id)
  }

  def getAll(company:String):Future[Seq[Office]] ={
    OfficeRepository.findOffices(company)
  }

}
