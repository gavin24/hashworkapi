import play.api.libs.json.Json

case class Race(id:String,name:String)
object Race{
  implicit val raceFmt = Json.format[Race]
}