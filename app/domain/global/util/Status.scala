import play.api.libs.json.Json

case class Status(id:String,name:String,value:String)
object Status{
  implicit val statFmt = Json.format[Status]
}