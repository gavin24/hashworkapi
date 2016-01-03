package controllers.people

import domain.people.PersonAttachment
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.PersonAttachmentService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/03.
 */
class PersonAttachmentController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonAttachment](input).get
      val results = PersonAttachmentService.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getPersonAttachment(company: String, personId: String, id: String) = Action.async {
    request =>
      PersonAttachmentService.getPersonAttachment(company, personId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getPersonAttachments(company: String, personId: String) = Action.async {
    request =>
      PersonAttachmentService.getPersonAttachments(company, personId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getCompanyPeopleAttachments(company: String) = Action.async {
    request =>
      PersonAttachmentService.getCompanyPeopleAttachments(company) map (result =>
        Ok(Json.toJson(result)))
  }
}
