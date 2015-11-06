package domain.people

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class PersonRole(personId: String,
                       roleId: String
                       )

object PersonRole {
  implicit val personroleFmt = Json.format[PersonRole]

}
