package controllers.people

import domain.people.PersonIdentity
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.PersonIdentityService

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonIdentityController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonIdentity](input).get
      val results = PersonIdentityService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonIdentityService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonIdentityService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }
}
