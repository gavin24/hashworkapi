package controllers.people

import domain.people.PersonEmploymentHistory
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.PersonEmploymentHistoryService

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonEmploymentHistoryController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonEmploymentHistory](input).get
      val results = PersonEmploymentHistoryService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonEmploymentHistoryService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonEmploymentHistoryService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }
}
