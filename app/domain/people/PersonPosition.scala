package domain.people

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class PersonPosition(
                         personId:String,
                         id:String,
                         positionId:String,
                         statusDate:Date,
                         statusId:String,
                         reason:String
                           )
object PersonPosition{
  implicit val personPosFmt = Json.format[PersonPosition]

}
