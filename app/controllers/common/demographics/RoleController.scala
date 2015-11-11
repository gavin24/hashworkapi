package controllers.common.demographics

import domain.common.demographics.Role
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.{GenderService, RoleService}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class RoleController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Role](input).get
      val results = RoleService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      RoleService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      RoleService.getAll map (result =>
        Ok(Json.toJson(result)))
  }
}
