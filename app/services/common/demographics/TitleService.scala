package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.{Gender, Title}
import repository.common.demographics.{GenderRepository, TitleRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object TitleService extends Service{
  def saveOrUpdate(entity: Title): Future[ResultSet] = {
    TitleRepository.save(entity)
  }
  def get(id:String):Future[Option[Gender]] ={
    GenderRepository.findById(id)
  }

  def getAll:Future[Seq[Gender]] ={
    GenderRepository.findAll
  }

}
