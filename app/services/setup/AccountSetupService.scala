package services.setup

import java.util.UUID

import conf.util.Util
import domain.common.demographics.Role
import domain.people.{PersonRole, Person}
import repository.common.demographics.RoleRepository
import repository.people.{PersonRoleRepository, PersonRepository}
import services.Service
import domain.common.demographics.RolesList._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object AccountSetupService extends Service {


  def createRoles = {
    val admin = Role(ROLE_ADMIN, ROLE_ADMIN, ADMIN)
    val prospect = Role(ROLE_PROSPECT, ROLE_PROSPECT, PROSPECT)
    val hrmanager = Role(ROLE_HRMANAGER, ROLE_HRMANAGER, HRMANAGER)
    val companyadmin = Role(ROLE_COMPANY_ADMIN, ROLE_COMPANY_ADMIN, COMPANY_ADMIN)
    val hrstaff = Role(ROLE_HRSTAFF, ROLE_HRSTAFF, HRSTAFF)
    val manager = Role(ROLE_MANAGER, ROLE_MANAGER, MANAGER)
    val employee = Role(ROLE_EMPLOYEE, ROLE_EMPLOYEE, EMPLOYEE)
    val trainer = Role(ROLE_TRAINER, ROLE_TRAINER, TRAINER)
    val panelist = Role(ROLE_PANELIST, ROLE_PANELIST, PANELIST)
    val repo = RoleRepository
    for {
      result1 <- repo.save(admin)
      result2 <- repo.save(prospect)
      result3 <- repo.save(hrmanager)
      result4 <- repo.save(companyadmin)
      result5 <- repo.save(hrstaff)
      result6 <- repo.save(manager)
      result7 <- repo.save(employee)
      result8 <- repo.save(trainer)
      result9 <- repo.save(panelist)

    } yield result9

  }

  def createAdmin = {

    val person = Person(
      "HASHCODE",
      Util.md5Hash("ADMIN"),
      "System",
      "Admin",
      "admin@test.com",
      "Administrator", "MR", Util.encode("admin"), true, true, true, true)

    val personrole = PersonRole("HASHCODE", ROLE_ADMIN)

    val prepo = PersonRepository
    val rrepo = PersonRoleRepository

    for {
      r <- prepo.save(person)
      pr <- rrepo.save(personrole)
    }
      yield pr

  }

}
