package services.global.demographics

import com.datastax.driver.core.ResultSet
import domain.global.demographics.LanguageProficiency
import repository.global.demographics.LanguageProficiencyRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LanguageProficiencyService extends Service{

  def saveOrUpdate(entity: LanguageProficiency): Future[ResultSet] = {
    LanguageProficiencyRepository.save(entity)
  }
}
