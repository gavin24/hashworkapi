package services.storage

import java.io.{FileOutputStream, File, FileInputStream}
import java.nio.ByteBuffer
import java.util.UUID
import javax.imageio.ImageIO

import com.sksamuel.scrimage.{Format, FormatDetector}
import conf.util.Util
import domain.storage.{CompanyFiles, FileMeta, FileResults}
import org.apache.commons.io.IOUtils
import org.imgscalr.Scalr
import repository.storage.CompanyFilesRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/30.
 */
object FileServices {


  def getFile(company:String, id: String): Future[Option[CompanyFiles]] = {
   StorageService.findFileById(company,id)
  }

  def processFile(data: FileInputStream, meta: FileMeta): Future[Seq[FileResults]] = {

    import scala.concurrent.ExecutionContext.Implicits.global
    meta.contentType.startsWith("image") match {
      case true => Future {

        val file = new File("/tmp/tmp" + UUID.randomUUID().toString +meta.fileName)


        val ext = getFileExtension(file)
        val normal = resizeImage(file, ext, 650)
        val thumb = resizeImage(file, ext, 150)


        val thumbnailImageId = FilesRepository.save(new FileInputStream(thumb), meta)
        val thumbnailImageMetaData = FileResults(
          thumbnailImageId.toString,
          "/api/static/" + thumbnailImageId.toString+"/"+getFileName(thumbnailImageId.toString),
          Some("Thumbnail"))

        val normalImageId = FilesRepository.save(new FileInputStream(normal), meta)

        val normalImageMetaData = FileResults(normalImageId.toString,
          "/api/static/" + normalImageId.toString+"/"+getFileName(normalImageId.toString),
          Some("Standard"))

        Seq[FileResults](normalImageMetaData, thumbnailImageMetaData)
      }
      case false => Future {
        val bytesBufferFile = ByteBuffer.wrap(IOUtils.toByteArray(data))

        val fileData:CompanyFiles = {
         CompanyFiles(
           meta.company,
           Util.md5Hash(UUID.randomUUID().toString),
           bytesBufferFile,
           meta.fileName,
           meta.contentType)

        }
        CompanyFilesRepository.save(fileData)
        val fileMetaData = FileResults(fileData.id,
          "/api/static/" + fileData.id+"/"+fileData.filename,
          None)
        Seq[FileResults](fileMetaData)
      }
    }
  }

  private def checkMimeType(file: Any): String = {
    FileTypeService.detectFile(file)
  }

  private def getFileExtension(file: File): String = {
    val format = FormatDetector.detect(new FileInputStream(file))
    format match {
      case Some(Format.GIF) => "gif"
      case Some(Format.PNG) => "png"
      case Some(Format.JPEG) => "jpg"
      case _ => "jpeg"
    }
  }

  private def resizeImage(file: File, ext: String, size: Int): File = {
    val image = ImageIO.read(file)
    val thumbnail = Scalr.resize(image, size)
    val resizedImage = new File("/tmp/tmp" + UUID.randomUUID().toString +"."+ ext)

    ImageIO.write(thumbnail, ext, resizedImage)
    resizedImage
  }

}