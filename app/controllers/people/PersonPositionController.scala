package controllers.people

import domain.people.PersonPosition
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.PersonPositionService

/**
 * Created by hashcode on 2016/01/11.
 */
class PersonPositionController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val entity = Json.fromJson[PersonPosition](request.body).get
      PersonPositionService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(personId: String, id: String) = Action.async {
    request =>
      PersonPositionService.getPersonPosition(personId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(personId: String) = Action.async {
    request =>
      PersonPositionService.getPersonPositions(personId) map (result =>
        Ok(Json.toJson(result)))
  }
}
