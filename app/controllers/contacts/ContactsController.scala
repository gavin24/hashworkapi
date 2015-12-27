package controllers.contacts

import domain.companycontacts.CompanyContacts
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.GenderService
import services.companycontacts.CompanyContactsService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/08.
 */
class ContactsController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[CompanyContacts](input).get
      val results = CompanyContactsService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(company: String, entityId: String, id: String) = Action.async {
    request =>
      CompanyContactsService.findById(company, entityId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(company: String) = Action.async {
    request =>
      CompanyContactsService.getAll(company) map (result =>
        Ok(Json.toJson(result)))
  }

  def findEntityContacts(company: String, entityId: String) = Action.async {
    request =>
      CompanyContactsService.findEntityContacts(company, entityId) map (result =>
        Ok(Json.toJson(result)))
  }

}
