package controllers.job

import domain.job.JobEvent
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.job.JobEventService

/**
 * Created by hashcode on 2016/01/11.
 */
class JobEventController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val entity = Json.fromJson[JobEvent](request.body).get
      JobEventService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(jobId: String, id: String) = Action.async {
    request =>
      JobEventService.getJobEventById(jobId, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(jobId: String) = Action.async {
    request =>
      JobEventService.getJobEvents(jobId) map (result =>
        Ok(Json.toJson(result)))
  }
}
