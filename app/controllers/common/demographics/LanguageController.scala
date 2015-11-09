package controllers.common.demographics

import domain.common.demographics.Language
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.LanguageService

import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by hashcode on 2015/11/09.
 */
class LanguageController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Language](input).get
      val results = LanguageService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

}
