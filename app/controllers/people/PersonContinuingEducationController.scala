package controllers.people

import domain.people.PersonContinuingEducation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.PersonContinuingEducationService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonContinuingEducationController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonContinuingEducation](input).get
      val results = PersonContinuingEducationService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonContinuingEducationService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonContinuingEducationService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }
}
