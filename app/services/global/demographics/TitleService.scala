package services.global.demographics

import com.datastax.driver.core.ResultSet
import domain.global.demographics.Title
import repository.global.demographics.TitleRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object TitleService extends Service{
  def saveOrUpdate(entity: Title): Future[ResultSet] = {
    TitleRepository.save(entity)
  }

}
