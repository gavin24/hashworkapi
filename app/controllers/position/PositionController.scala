package controllers.position

import domain.common.demographics.Gender
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.BodyParsers.parse
import services.common.demographics.GenderService

/**
 * Created by hashcode on 2016/01/11.
 */
class PositionController {
  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[Gender](request.body).get
      GenderService.saveOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      GenderService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      GenderService.getAll map (result =>
        Ok(Json.toJson(result)))
  }
}
