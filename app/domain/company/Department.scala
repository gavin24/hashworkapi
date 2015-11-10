package domain.company

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class Department( company:String,
                       id: String,
                       name: String,
                       description: String,
                       active: Boolean,state:String
                       )
object Department {
  implicit val  departmentFmt = Json.format[Department]
}
