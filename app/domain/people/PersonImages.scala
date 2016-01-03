package domain.people

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/03.
 */
case class PersonImages(company: String,
                        personId:String,
                        id: String,
                        url: String,
                        size: Option[String],
                        mime: String,
                        date: Date)

object PersonImages{
  implicit val companyImagesFmt = Json.format[PersonImages]

}
