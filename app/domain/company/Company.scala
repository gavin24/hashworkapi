package domain.company

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/29.
 */
case class Company(
                    id: String,
                    name: String,
                    details:Map[String,String]
                    )
object Company {
  implicit val companyFmt = Json.format[Company]



}
