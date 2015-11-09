package controllers.common.location

import domain.common.location.LocationType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.location.LocationTypeService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/08.
 */
class LocationTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[LocationType](input).get
      val results = LocationTypeService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

}
