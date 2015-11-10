package domain.people

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/11/10.
 */
case class PersonDemographics(id: String,
                              personId: String,
                              genderId: String,
                              dateOfBirth: Date,
                              maritalStatusId: String,
                              numberOfDependencies: Int,
                              dateCreated: Date)

object PersonDemographics {
  implicit val personFmt = Json.format[PersonDemographics]

}
