package services.common.util

import com.datastax.driver.core.ResultSet
import domain.common.demographics.Gender
import domain.common.util.Currency
import repository.common.demographics.GenderRepository
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
  def get(id:String):Future[Option[Gender]] ={
    GenderRepository.findById(id)
  }

  def getAll:Future[Seq[Gender]] ={
    GenderRepository.findAll
  }
}
