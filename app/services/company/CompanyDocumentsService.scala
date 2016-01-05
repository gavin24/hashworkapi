package services.company

import com.datastax.driver.core.ResultSet
import domain.company.CompanyDocuments
import repository.company.CompanyDocumentsRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/05.
 */
object CompanyDocumentsService extends Service {

  def save(doc: CompanyDocuments): Future[ResultSet] = {
    CompanyDocumentsRepository.save(doc)
  }

  def getCompanyDocumentById(company: String, id: String): Future[Option[CompanyDocuments]] = {
    CompanyDocumentsRepository.getCompanyDocumentById(company, id)
  }

  def getCompanyDocuments(company: String): Future[Seq[CompanyDocuments]] = {
    CompanyDocumentsRepository.getCompanyDocuments(company)
  }

}
