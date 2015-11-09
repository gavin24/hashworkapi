package controllers.common.location

import domain.common.location.ContactType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.location.ContactTypeService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class ContactTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[ContactType](input).get
      val results = ContactTypeService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

}
