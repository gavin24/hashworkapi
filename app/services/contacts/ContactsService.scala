package services.contacts

import com.datastax.driver.core.ResultSet
import domain.contacts.Contacts
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

}
