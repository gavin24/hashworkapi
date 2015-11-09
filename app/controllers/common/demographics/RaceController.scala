package controllers.common.demographics

import domain.common.demographics.Race
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.RaceService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class RaceController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Race](input).get
      val results = RaceService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

}
