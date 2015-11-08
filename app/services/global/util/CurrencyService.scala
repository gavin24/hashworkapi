package services.global.util

import com.datastax.driver.core.ResultSet
import domain.global.util.Currency
import repository.global.util.CurrencyRepository
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
