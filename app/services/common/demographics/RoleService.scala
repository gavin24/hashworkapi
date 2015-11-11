package services.common.demographics

import com.datastax.driver.core.ResultSet
import domain.common.demographics.{Gender, Role}
import repository.common.demographics.{GenderRepository, RoleRepository}
import services.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object RoleService extends Service{

  def saveOrUpdate(entity: Role): Future[ResultSet] = {
    RoleRepository.save(entity)
  }
  def get(id:String):Future[Option[Role]] ={
    RoleRepository.findById(id)
  }

  def getAll:Future[Seq[Role]] ={
    RoleRepository.findAll
  }
}
