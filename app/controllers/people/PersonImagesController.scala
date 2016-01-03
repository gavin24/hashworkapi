package controllers.people

import domain.people.PersonImages
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.PersonImagesService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/03.
 */
class PersonImagesController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonImages](input).get
      val results = PersonImagesService.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getPersonImage(company: String, personId: String, id: String) = Action.async {
    request =>
      PersonImagesService.getPersonImage(company, personId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getPersonImages(company: String, id: String) = Action.async {
    request =>
      PersonImagesService.getPersonImages(company, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getCompanyPeopleImages(company: String) = Action.async {
    request =>
      PersonImagesService.getCompanyPeopleImages(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
