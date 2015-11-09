package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.LanguageProficiency
import repository.common.demographics.LanguageProficiencyRepository
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
