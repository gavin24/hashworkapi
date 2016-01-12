package controllers.position

import domain.position.PositionDesignation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.position.PositionDesignationService

/**
 * Created by hashcode on 2016/01/11.
 */
class PositionDesignationController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[PositionDesignation](request.body).get
      PositionDesignationService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(positionId: String, id: String) = Action.async {
    request =>
      PositionDesignationService.getDesignationById(positionId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(positionId: String) = Action.async {
    request =>
      PositionDesignationService.getPositionDesignations(positionId) map (result =>
        Ok(Json.toJson(result)))
  }
}
