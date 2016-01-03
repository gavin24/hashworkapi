package services.people

import com.websudos.phantom.dsl._
import domain.people.PersonAttachment
import repository.people.PersonAttachmentRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
object PersonAttachmentService {
  def save(attachment: PersonAttachment): Future[ResultSet] = {
    PersonAttachmentRepository.save(attachment)
  }

  def getPersonAttachment(company: String, personId: String, id: String): Future[Option[PersonAttachment]] = {
    PersonAttachmentRepository.getAttachment(company, personId, id)
  }

  def getPersonAttachments(company: String, personId: String): Future[Seq[PersonAttachment]] = {
    PersonAttachmentRepository.getPersonAttachments(company, personId)
  }

  def getCompanyPeopleAttachments(company: String): Future[Seq[PersonAttachment]] = {
    PersonAttachmentRepository.getCompanyPeopleAttachments(company)
  }

}
