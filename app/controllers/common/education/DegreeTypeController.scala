package controllers.common.education


import domain.common.education.DegreeType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.education.DegreeTypeService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/12/13.
 */
class DegreeTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[DegreeType](input).get
      val results = DegreeTypeService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      DegreeTypeService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      DegreeTypeService.getAll map (result =>
        Ok(Json.toJson(result)))
  }

}
