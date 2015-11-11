package services.contacts

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import domain.contacts.Contacts
import repository.common.demographics.GenderRepository
import repository.contacts.ContactsRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object ContactsService extends Service {

  def saveOrUpdate(entity: Contacts): Future[ResultSet] = {
    ContactsRepository.save(entity)
  }
  def get(company:String,id:String):Future[Option[Contacts]] ={
    ContactsRepository.findById(company,id)
  }

  def getAll(company:String):Future[Seq[Contacts]] ={
    ContactsRepository.findAll(company)
  }

}
