package controllers.common.demographics

import domain.common.demographics.IdentificationType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.IdentificationTypeService

import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by hashcode on 2015/11/09.
 */
class IdentificationTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[IdentificationType](input).get
      val results = IdentificationTypeService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

}
