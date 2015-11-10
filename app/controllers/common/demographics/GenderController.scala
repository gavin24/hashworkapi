package controllers.common.demographics

import domain.common.demographics.Gender
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.GenderService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class GenderController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Gender](input).get
      val results = GenderService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getGender(id: String) = Action.async {
    val results = GenderService.get(id)
    results map (result =>
      Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    val genders = GenderService.getAll
    genders map (gender =>
      Ok(Json.toJson(gender)))
  }

}
