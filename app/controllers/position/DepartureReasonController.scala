package controllers.position

import domain.position.DepartureReason
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.position.DepartureReasonService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/11.
 */
class DepartureReasonController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[DepartureReason](request.body).get
      DepartureReasonService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(company: String, id: String) = Action.async {
    request =>
      DepartureReasonService.getDepartureReason(company, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(company: String) = Action.async {
    request =>
      DepartureReasonService.getCompanyDepatureReasons(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
