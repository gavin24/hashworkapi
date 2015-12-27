package domain.companycontacts

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class CompanyContacts(
                            company: String,
                            entityId: String,
                            id: String,
                            postalAddress: Map[String, String],
                            physicalAddress: Map[String, String],
                            contactNumber: Map[String, String],
                            emailAddress: Map[String, String],
                            state: String,
                            lastupdate: Date
                            )

object CompanyContacts {
  implicit val contactsFmt = Json.format[CompanyContacts]

}
