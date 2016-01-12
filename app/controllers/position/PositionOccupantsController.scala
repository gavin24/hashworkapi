package controllers.position

import domain.position.PositionOccupants
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.position.PositionOccupantsService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/11.
 */
class PositionOccupantsController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[PositionOccupants](request.body).get
      PositionOccupantsService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(positionId: String, id: String) = Action.async {
    request =>
      PositionOccupantsService.getPositionOccupant(positionId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(positionId: String) = Action.async {
    request =>
      PositionOccupantsService.getPositionOccupants(positionId) map (result =>
        Ok(Json.toJson(result)))
  }
}
