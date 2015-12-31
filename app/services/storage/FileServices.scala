package services.storage

import java.io.{FileOutputStream, File, FileInputStream}
import java.nio.ByteBuffer
import java.util.UUID
import javax.imageio.ImageIO

import com.sksamuel.scrimage.{Format, FormatDetector}
import conf.util.Util
import domain.storage.{CompanyImages, CompanyFiles, FileMeta, FileResults}
import org.apache.commons.io.{FileUtils, IOUtils}
import org.imgscalr.Scalr
import repository.storage.{CompanyImagesRepository, CompanyFilesRepository}

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/12/30.
 */
object FileServices {


  def getFile(company: String, id: String): Future[Option[CompanyFiles]] = {
    StorageService.findFileById(company, id)
  }

  def processImage(data: File, meta: FileMeta): Future[Seq[FileResults]] = {

    import scala.concurrent.ExecutionContext.Implicits.global
    meta.contentType.startsWith("image") match {
      case true => Future {
        val ext = getFileExtension(data)
        val normal = resizeImage(data, ext, 650)
        val thumb = resizeImage(data, ext, 150)

        val thumBytes = ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(thumb)))

        val thumbDataImage = CompanyImages(
          meta.company,
          Util.md5Hash(UUID.randomUUID().toString),
          thumBytes,
          meta.fileName,
          meta.contentType
        )

        val normalBytes =  ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(normal)))

        val normalDataImage = CompanyImages(
          meta.company,
          Util.md5Hash(UUID.randomUUID().toString),
          normalBytes,
          meta.fileName,
          meta.contentType
        )

       CompanyImagesRepository.save(thumbDataImage)
        val thumbnailImageMetaData = FileResults(
          thumbDataImage.id,
          "/api/static/" + thumbDataImage.id+"/"+thumbDataImage.filename,
          Some("Thumbnail"))

        CompanyImagesRepository.save(normalDataImage)

        val normalImageMetaData = FileResults(normalDataImage.id,
          "/api/static/" + normalDataImage.id+"/"+normalDataImage.filename,
          Some("Standard"))

        Seq[FileResults](normalImageMetaData, thumbnailImageMetaData)
      }
      case false =>
    }
  }


  def processFile(data: FileInputStream, meta: FileMeta): Future[Seq[FileResults]] = {
    Future {
      val bytesBufferFile = ByteBuffer.wrap(IOUtils.toByteArray(data))
      val fileData: CompanyFiles = {
        CompanyFiles(
          meta.company,
          Util.md5Hash(UUID.randomUUID().toString),
          bytesBufferFile,
          meta.fileName,
          meta.contentType)
      }
      CompanyFilesRepository.save(fileData)
      val fileMetaData = FileResults(fileData.id,
        "/api/static/" + fileData.id + "/" + fileData.filename,
        None)
      Seq[FileResults](fileMetaData)
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
    val resizedImage = new File("/tmp/tmp" + UUID.randomUUID().toString + "." + ext)

    ImageIO.write(thumbnail, ext, resizedImage)
    resizedImage
  }

}