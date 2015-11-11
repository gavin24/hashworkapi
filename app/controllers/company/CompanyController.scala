package controllers.company

import domain.company.Company
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.GenderService
import services.company.CompanyService
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by hashcode on 2015/11/08.
 */
class CompanyController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Company](input).get
      val results = CompanyService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      CompanyService.getById(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      CompanyService.getAll map (result =>
        Ok(Json.toJson(result)))
  }

  def isAvailable(name:String) = Action.async {
    request =>
      CompanyService.isAvailable(name) map (result =>
        Ok(Json.toJson(result)))
  }
}
