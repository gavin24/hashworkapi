package domain.contacts

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class Contacts(company: String,
                    id: String,
                    postalAddress: Map[String,String],
                    physicalAddress: Map[String,String],
                    contactNumber: Map[String,String],
                    postalCode: Map[String,String],
                    emailAddress: Map[String,String],state:String
                     )
object Contacts {
  implicit val contactsFmt = Json.format[Contacts]

}
