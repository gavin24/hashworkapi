package controllers.storage

import java.io.FileInputStream
import java.text.SimpleDateFormat

import play.api.libs.iteratee.Enumerator
import play.api.mvc.{Action, ResponseHeader, Result, Controller}
import services.storage.{FileServices, StorageService}

/**
 * Created by hashcode on 2015/12/30.
 */
class FileStorageController extends Controller{

  def getFile(company: String, id:String) = Action {

    import scala.concurrent.ExecutionContext.Implicits.global

    FileServices.getFile(company,id) map ( cp => {

      cp match {
        case Some(file) => Result(
            val fs = FileInputStream(file.file)
          ResponseHeader(OK, Map(
            CONTENT_LENGTH -> file.file.le
              CONTENT_TYPE -> file.contentType.getOrElse(BINARY),
            DATE -> new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", java.util.Locale.US).format(file.uploadDate)
          )),
          Enumerator.fromStream(file.inputStream)
        )
        case None => NotFound
      }


    })
  }

}
