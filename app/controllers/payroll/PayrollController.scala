package controllers.payroll

import domain.company.Company
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.company.CompanyService

import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by hashcode on 2015/11/08.
 */
class PayrollController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Company](input).get
      val results = CompanyService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

}
