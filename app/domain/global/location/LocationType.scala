package domain.global.location

import play.api.libs.json.Json

case class LocationType(id:String, name:String,code:String)
object LocationType{
  implicit val locatFmt = Json.format[LocationType]
}