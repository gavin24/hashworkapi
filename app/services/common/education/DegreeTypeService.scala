package services.common.education

import com.datastax.driver.core.ResultSet
import domain.common.education.DegreeType
import repository.common.education.DegreeTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/13.
 */
object DegreeTypeService extends Service {

  def saveOrUpdate(entity: DegreeType): Future[ResultSet] = {
    DegreeTypeRepository.save(entity)
  }

  def get(id: String): Future[Option[DegreeType]] = {
    DegreeTypeRepository.findById(id)
  }

  def getAll: Future[Seq[DegreeType]] = {
    DegreeTypeRepository.findAll
  }
}