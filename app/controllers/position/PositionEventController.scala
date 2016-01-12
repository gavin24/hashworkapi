package controllers.position

import domain.common.demographics.Gender
import domain.position.{PositionEvent, PositionDesignation}
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}
import play.api.mvc.BodyParsers.parse
import services.common.demographics.GenderService
import services.job.JobService
import services.position.{PositionEventService, PositionDesignationService}

/**
 * Created by hashcode on 2016/01/11.
 */
class PositionEventController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[PositionEvent](request.body).get
      PositionEventService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(positionId: String, id: String) = Action.async {
    request =>
      PositionEventService.getPositionEvent(positionId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(positionId: String) = Action.async {
    request =>
      PositionEventService.getPositionEvents(positionId) map (result =>
        Ok(Json.toJson(result)))
  }
}
