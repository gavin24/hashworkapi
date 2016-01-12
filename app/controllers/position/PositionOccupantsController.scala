package controllers.position

import domain.common.demographics.Gender
import domain.position.{PositionOccupants, PositionDesignation}
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}
import play.api.mvc.BodyParsers.parse
import services.common.demographics.GenderService
import services.job.JobService
import services.position.{PositionOccupantsService, PositionDesignationService}

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
