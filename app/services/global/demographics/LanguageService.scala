package services.global.demographics

import com.datastax.driver.core.ResultSet
import domain.global.demographics.Language
import repository.global.demographics.LanguageRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LanguageService extends Service{
  def saveOrUpdate(entity: Language): Future[ResultSet] = {
    LanguageRepository.save(entity)
  }

}
