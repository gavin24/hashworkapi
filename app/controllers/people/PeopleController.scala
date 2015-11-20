package controllers.people

import domain.people.Person
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.common.demographics.GenderService
import services.people.PeopleService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/08.
 */
class PeopleController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Person](input).get
      val results = PeopleService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getCompanyPeople(id: String) = Action.async {
    request =>
      PeopleService.getPeople(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getPerson(company:String,id:String) = Action.async {
    request =>
      PeopleService.getPerson(company,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getPersonByEmail(email:String) = Action.async {
    request =>
      PeopleService.getPersonByEmail(email) map (result =>
        Ok(Json.toJson(result)))
  }


}
