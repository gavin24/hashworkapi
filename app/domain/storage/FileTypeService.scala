package domain.storage

import java.io.{InputStream, File}

import org.apache.tika.Tika

/**
 * Created by hashcode on 2015/12/30.
 */
object FileTypeService {
  val detect = new Tika()

  def detectFile(file:Any):String={
    file match {
      case value:String => detect.detect(value)
      case value:File => detect.detect(value)
      case value:InputStream => detect.detect(value)
    }
  }
}
