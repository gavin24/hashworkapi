package services.setup


import repository.company.{CompanyRepository, OfficeRepository, DepartmentRepository}
import repository.contacts.ContactsRepository
import repository.common.demographics._
import repository.common.education.{EvaluationRepository, EducationTypeRepository}
import repository.common.job.JobClassificationRepository
import repository.common.location.{ContactTypeRepository, AddressTypeRepository, LocationTypeRepository}
import repository.common.position.PositionTypeRepository
import repository.common.util.{MailRepository, CurrencyRepository, StatusRepository}
import repository.people.{UsersRepository, PersonRoleRepository, PersonRepository}

import services.Service
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object SchemaSetUpService extends Service {

  def createCompanySchema = for {
  //company
    person <- PersonRepository.create.ifNotExists().future()
    depart <- DepartmentRepository.create.ifNotExists().future()
    office <- OfficeRepository.create.ifNotExists().future()
    //contacts
    contacts <- ContactsRepository.create.ifNotExists().future()
    // demo
    gender <- GenderRepository.create.ifNotExists().future()
    id <- IdentificationTypeRepository.create.ifNotExists().future()
    lanp <- LanguageProficiencyRepository.create.ifNotExists().future()
    //contacts
    lang <- LanguageRepository.create.ifNotExists().future()
    marital <- MaritalStatusRepository.create.ifNotExists().future()
    depart <- RaceRepository.create.ifNotExists().future()
    role <- RoleRepository.create.ifNotExists().future()
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
    role <- RoleRepository.create.ifNotExists().future()

    //position
    postype <- PositionTypeRepository.create.ifNotExists().future()
    title <- TitleRepository.create.ifNotExists().future()

    //util
    status <- StatusRepository.create.ifNotExists().future()
    currency <- CurrencyRepository.create.ifNotExists().future()

    //people
    emailp <- UsersRepository.create.ifNotExists().future()
    company <- CompanyRepository.create.ifNotExists().future()
    locatype <- LocationTypeRepository.create.ifNotExists().future()
    addretype <- AddressTypeRepository.create.ifNotExists().future()
    mail <-MailRepository.create.ifNotExists().future()
    ctypes <- ContactTypeRepository.create.ifNotExists().future()
  } yield person
}
