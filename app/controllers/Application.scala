package controllers

import play.api.libs.json.Json
import play.api.mvc._
import services.setup.SchemaSetUpService
import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def setup = Action.async {
    val results = for {
    //Comments
      person <-SchemaSetUpService.createCompanySchema

    } yield (person)
    results map (result => {
      Ok(Json.toJson("Done"))
    })
  }

  def options(path: String) = Action {
    Ok("")
  }

}
