package controllers

import play.api.libs.json.Json
import play.api.mvc._
import services.setup.{AccountSetupService, SchemaSetUpService}
import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def setup = Action.async {
    val results = for {
      setup <-SchemaSetUpService.createCompanySchema
      roles <- AccountSetupService.createRoles
      user <-AccountSetupService.createAdmin
    } yield (setup)
    results map (result => {
      Ok(Json.toJson(result.isExhausted))
    })
  }

  def options(path: String) = Action {
    Ok("")
  }

}
