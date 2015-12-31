package controllers.storage

import java.text.SimpleDateFormat

import domain.storage.FileMeta
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Json
import play.api.mvc.{ResponseHeader, Result, Action, Controller}
import services.storage.{FileTypeService, FileServices, StorageService}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/30.
 */
class ImageStorageController extends Controller{

  def upload = Action.async(parse.multipartFormData) { request =>
    import scala.concurrent.ExecutionContext.Implicits.global
    request.body.file("upload") match {
      case Some(file) => {
        val data = file.ref.file
        val meta = FileMeta(file.filename, FileTypeService.detectFile(data))
        val results = FileServices.processFile(data, meta)
        results map (result =>
          Ok(Json.toJson(result)))
      }
      case None => {
        Future {
          BadRequest
        }
      }
    }
  }

  def getFile(company: String, id:String) = Action {
    import scala.concurrent.ExecutionContext.Implicits.global
    StorageService.findFileById(company,id) match {
      case Some(file) => Result(
        ResponseHeader(OK, Map(
          CONTENT_LENGTH -> file.length.toString,
          CONTENT_TYPE -> file.contentType.getOrElse(BINARY),
          DATE -> new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", java.util.Locale.US).format(file.uploadDate)
        )),
        Enumerator.fromStream(file.inputStream)
      )
      case None => NotFound
    }
  }

}
