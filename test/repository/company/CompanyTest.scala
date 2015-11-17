package repository.company

import org.junit.Test
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by hashcode on 2015/11/17.
 */
class CompanyTest extends TestAPI{
  @Test
  def testCreateLocationType(): Unit = {
    val repo = CompanyRepository
    Await.result(repo.create.ifNotExists().future(), 5000.millis)


  }

}
