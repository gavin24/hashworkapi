package domain.common.position

import play.api.libs.json.Json

case class PositionType(id:String, name:String)
object PositionType{
  implicit val posFmt = Json.format[PositionType]
}