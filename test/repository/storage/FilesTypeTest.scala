package repository.storage

import java.io.{File, FileInputStream}
import javax.imageio.ImageIO


import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream
import com.websudos.phantom.util.ByteString
import domain.storage.CompanyImages
import org.apache.commons.io.IOUtils
import org.junit.Test
import repository.company.TestAPI

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by hashcode on 2015/12/30.
 */
class FilesTypeTest extends TestAPI{

  @Test
  def testFileType(): Unit = {

    val res = Await.result(CompanyImagesRepository.create.ifNotExists().future(), 5000.millis)
    val res2 = Await.result(CompanyFilesRepository.create.ifNotExists().future(), 5000.millis)

//    val url = getClass().getResource("Kalengo.jpg")
//    val file = new File(url.getPath())
//    val logo = new FileInputStream(file)
//
//    val bytesflie = IOUtils.toByteArray(logo)
//
//    val b = ByteString(bytesflie)
//
//
//    val c = b.asByteBuffer
//
//    val imagef = CompanyImages("Hcode", "123", c, b)
//
//    //    CompanyImagesRepository.save(imagef)
//
//    val repo = CompanyImagesRepository
//
//    val res = Await.result(repo.findFileById("Hcode", "123"), 5000.millis)
//
//    val imageString = res match {
//      case Some(v) => v.imageBuffer
//    }
//
//    val imageBuffer = res match {
//      case Some(v) => v.image
//    }
//
//    val image1 = new ByteBufferBackedInputStream(imageBuffer)
//
//    val image2 = new ByteBufferBackedInputStream(imageString.asByteBuffer)
//







    //    val mimeType = FileTypeService.detectFile("http://localhost:9000/cdn/56095b6ebe24baa41da57f13")

    //    val outfile = FileServices.createImage(file)
    //    val outfileThumb = FileServices.createThumb(file)
//    val image = ImageIO.read(image1)
//    val imageb = ImageIO.read(image2)
//    ImageIO.write(image, "jpg", new File("/home/hashcode/hashprojects/hashworkapi/headerle.jpg"))
//    ImageIO.write(imageb, "jpg", new File("/home/hashcode/hashprojects/hashworkapi/thumb.jpg"))


  }

}
