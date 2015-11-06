import play.api.libs.json.Json

case class MaritalStatus(id:String,name:String)

object MaritalStatus{
  implicit val maritFmt = Json.format[MaritalStatus]
}