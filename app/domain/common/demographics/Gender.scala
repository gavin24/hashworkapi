package domain.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */


import play.api.libs.json.Json

case class Gender(id:String,name:String,state:String)
object Gender{
  implicit val genderFmt = Json.format[Gender]
}