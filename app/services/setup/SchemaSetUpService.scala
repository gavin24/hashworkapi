package services.setup

import repository.company.{OfficeRepository, DepartmentRepository}
import repository.people.PersonRepository
import services.Service
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object SchemaSetUpService extends Service{
  def createCompanySchema={
     for{
      person <-PersonRepository.create.ifNotExists().future()
      depart <-DepartmentRepository.create.ifNotExists().future()
      office <-OfficeRepository.create.ifNotExists().future()


    } yield person
  }
}
