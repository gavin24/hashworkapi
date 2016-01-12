package controllers.job

import domain.job.Job
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.job.JobService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/11.
 */
class JobController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val entity = Json.fromJson[Job](request.body).get
      JobService.createOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(company: String, id: String) = Action.async {
    request =>
      JobService.getJobById(company, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(company: String) = Action.async {
    request =>
      JobService.getCompanyJobs(company) map (result =>
        Ok(Json.toJson(result)))
  }

}
