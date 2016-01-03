package controllers.company

import domain.company.CompanyLogo
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.company.CompanyLogoServices
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/03.
 */
class CompanyLogoController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[CompanyLogo](input).get
      val results = CompanyLogoServices.SaveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getCompanyLogo(company:String, id: String) = Action.async {
    request =>
      CompanyLogoServices.findDCompanyLogo(company,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllCompanyLogos(company:String) = Action.async {
    request =>
      CompanyLogoServices.findCompanyLogos(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
