package domain.company

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class Office( company:String,
                   id: String,
                   name: String,
                   description: String,
                   active: Boolean,
                   officeTypeId: String,
                   contactId: String
                   )
object Office {
  implicit val officeFmt = Json.format[Office]

}
