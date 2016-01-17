package controllers.position

import domain.position.Position
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.position.PositionService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/11.
 */
class PositionController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val entity = Json.fromJson[Position](request.body).get
      PositionService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def batchCreate = Action.async(parse.json) {
    request =>
      val entities = Json.fromJson[Set[Position]](request.body).get

      entities.foreach(position=> println("The Code is ",position.code))

        Future{Ok("")}
  }

  def getById(company: String, id: String) = Action.async {
    request =>
      PositionService.getPositionById(company, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(company: String) = Action.async {
    request =>
      PositionService.getCompanyPositions(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
