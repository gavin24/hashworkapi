package services.people

import com.websudos.phantom.dsl._
import domain.people.PersonImages
import repository.people.PersonImagesRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
object PersonImagesService {
  def save(image: PersonImages): Future[ResultSet] = {
    PersonImagesRepository.save(image)
  }

  def getPersonImage(company: String, personId: String, id: String): Future[Option[PersonImages]] = {
    PersonImagesRepository.getPersonImage(company, personId, id)
  }

  def getPersonImages(company: String, personId: String): Future[Seq[PersonImages]] = {
    PersonImagesRepository.getPersonImages(company, personId)
  }

  def getCompanyPeopleImages(company: String): Future[Seq[PersonImages]] = {
    PersonImagesRepository.getCompanyPeopleImages(company)
  }

}
