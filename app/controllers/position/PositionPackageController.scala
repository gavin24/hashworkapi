package controllers.position

import domain.position.PositionPackage
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.position.PositionPackageService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/11.
 */
class PositionPackageController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[PositionPackage](request.body).get
      PositionPackageService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(positionId: String, id: String) = Action.async {
    request =>
      PositionPackageService.getPositionPackage(positionId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(postionId: String) = Action.async {
    request =>
      PositionPackageService.getPositionPackages(postionId) map (result =>
        Ok(Json.toJson(result)))
  }
}
