package services.companycontacts

import com.datastax.driver.core.ResultSet
import domain.companycontacts.CompanyContacts
import repository.contacts.ContactsRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object CompanyContactsService extends Service {

  def saveOrUpdate(entity: CompanyContacts): Future[ResultSet] = {
    ContactsRepository.save(entity)
  }
  def findById(company: String, entityId:String, id: String):Future[Option[CompanyContacts]] ={
    ContactsRepository.findById(company,entityId,id)
  }

  def getAll(company:String):Future[Seq[CompanyContacts]] ={
    ContactsRepository.findAll(company)
  }

  def findEntityContacts(company: String, entityId:String): Future[Seq[CompanyContacts]] = {
    ContactsRepository.findEntityContacts(company,entityId)
  }

}
