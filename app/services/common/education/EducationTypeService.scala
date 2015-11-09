package services.common.education

import com.datastax.driver.core.ResultSet
import domain.common.education.EducationType
import repository.common.education.EducationTypeRepository
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object EducationTypeService extends Service{

  def saveOrUpdate(entity: EducationType): Future[ResultSet] = {
    EducationTypeRepository.save(entity)
  }
}
