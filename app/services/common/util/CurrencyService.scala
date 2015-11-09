package services.common.util

import com.datastax.driver.core.ResultSet
import domain.common.util.Currency
import repository.common.util.CurrencyRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object CurrencyService extends Service {
  def saveOrUpdate(entity: Currency): Future[ResultSet] = {
    CurrencyRepository.save(entity)
  }
}
