package services.storage

import com.datastax.driver.core.ResultSet
import domain.storage.{CompanyFiles, CompanyImages}
import repository.storage.{CompanyFilesRepository, CompanyImagesRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/30.
 */
object StorageService extends Service {

  def saveOrUpdateFile(entity: CompanyFiles): Future[ResultSet] = {
    CompanyFilesRepository.save(entity)
  }

  def saveOrUpdateImage(entity: CompanyImages): Future[ResultSet] = {
    CompanyImagesRepository.save(entity)
  }

  def findCompanyFiles(company: String): Future[Seq[CompanyFiles]] = {
    CompanyFilesRepository.findCompanyFiles(company)
  }

  def findFileById(company: String, id: String): Future[Option[CompanyFiles]] = {
    CompanyFilesRepository.findFileById(company, id)
  }

  def findCompanyImages(company: String): Future[Seq[CompanyImages]] = {
    CompanyImagesRepository.findCompanyImages(company)
  }

  def findImageById(company: String, id: String): Future[Option[CompanyImages]] = {
    CompanyImagesRepository.findImageById(company, id)
  }


}
