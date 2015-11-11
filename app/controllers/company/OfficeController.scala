package controllers.company

import domain.company.Office
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.GenderService
import services.company.OfficeService

import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by hashcode on 2015/11/09.
 */
class OfficeController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Office](input).get
      val results = OfficeService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(company:String,id: String) = Action.async {
    request =>
      OfficeService.getOffice(company,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllOffices(company:String) = Action.async {
    request =>
      OfficeService.getAll(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
