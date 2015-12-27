package controllers.company

import domain.company.Department
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.GenderService
import services.company.DepartmentService

import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by hashcode on 2015/11/09.
 */
class DepartmentController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Department](input).get
      val results = DepartmentService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(company:String, id: String) = Action.async {
    request =>
      DepartmentService.get(company,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllDepartments(company:String) = Action.async {
    request =>
      DepartmentService.getDepartments(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
