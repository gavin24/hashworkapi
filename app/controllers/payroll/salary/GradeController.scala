package controllers.payroll.salary

import domain.payroll.salary.Grade
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.payroll.salary.GradeService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/11.
 */
class GradeController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[Grade](request.body).get
      GradeService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(company: String, id: String) = Action.async {
    request =>
      GradeService.getGradeById(company, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(company: String) = Action.async {
    request =>
      GradeService.getCompanyGrades(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
