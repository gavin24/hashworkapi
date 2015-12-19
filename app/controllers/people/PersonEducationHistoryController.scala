package controllers.people

import domain.people.PersonEducationHistory
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.PersonEducationHistoryService

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonEducationHistoryController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonEducationHistory](input).get
      val results = PersonEducationHistoryService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonEducationHistoryService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonEducationHistoryService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }

}
