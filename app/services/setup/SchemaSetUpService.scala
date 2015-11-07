package services.setup


import repository.company.{OfficeRepository, DepartmentRepository}
import repository.contacts.ContactsRepository
import repository.global.demographics._
import repository.global.education.{EvaluationRepository, EducationTypeRepository}
import repository.global.job.JobClassificationRepository
import repository.global.location.{AddressTypeRepository, LocationTypeRepository}
import repository.global.position.PositionTypeRepository
import repository.global.util.{CurrencyRepository, StatusRepository}
import repository.people.{PersonRoleRepository, PersonRepository}

import services.Service
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object SchemaSetUpService extends Service{

  def createCompanySchema= for{
    //company
   person <-PersonRepository.create.ifNotExists().future()
   depart <-DepartmentRepository.create.ifNotExists().future()
   office <-OfficeRepository.create.ifNotExists().future()
    //contacts
   contacts <- ContactsRepository.create.ifNotExists().future()
    // demo
   gender <- GenderRepository.create.ifNotExists().future()
   id <-IdentificationTypeRepository.create.ifNotExists().future()
   lanp <-LanguageProficiencyRepository.create.ifNotExists().future()
   //contacts
   lang <- LanguageRepository.create.ifNotExists().future()
   marital <-MaritalStatusRepository.create.ifNotExists().future()
   depart <-RaceRepository.create.ifNotExists().future()
   role <-RoleRepository.create.ifNotExists().future()
   //contacts
   title <- StatusRepository.create.ifNotExists().future()
   preole <- PersonRoleRepository.create.ifNotExists().future()

    //education
  eduty <- EducationTypeRepository.create.ifNotExists().future()
  eval <- EvaluationRepository.create.ifNotExists().future()

    // job
  job <- JobClassificationRepository.create.ifNotExists().future()


    //location
  race <- RaceRepository.create.ifNotExists().future()
  role <-RoleRepository.create.ifNotExists().future()

    //position
  postype <- PositionTypeRepository.create.ifNotExists().future()
  title <-TitleRepository.create.ifNotExists().future()

    //util
   status <- StatusRepository.create.ifNotExists().future()
   currerency <- CurrencyRepository.create.ifNotExists().future()

    //people
   locatype <-LocationTypeRepository.create.ifNotExists().future()
   addretype <- AddressTypeRepository.create.ifNotExists().future()
 } yield person
}
