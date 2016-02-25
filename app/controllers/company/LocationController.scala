package controllers.company

import domain.company.Location
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.company.LocationService

/**
  * Created by hashcode on 2016/02/25.
  */
class LocationController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Location](input).get
      val results = LocationService.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(company:String, id: String) = Action.async {
    request =>
      LocationService.findById(company,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllDepartments(company:String) = Action.async {
    request =>
      LocationService.findAll(company) map (result =>
        Ok(Json.toJson(result)))
  }

}
