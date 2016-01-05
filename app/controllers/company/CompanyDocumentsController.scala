package controllers.company

import domain.company.CompanyDocuments
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.company.CompanyDocumentsService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/05.
 */
class CompanyDocumentsController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[CompanyDocuments](input).get
      val results = CompanyDocumentsService.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getDocuemtById(company:String, id: String) = Action.async {
    request =>
      CompanyDocumentsService.getCompanyDocumentById(company,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllCompanyDocuments(company:String) = Action.async {
    request =>
      CompanyDocumentsService.getCompanyDocuments(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
