import play.api.libs.json.Json

case class EducationType(id:String, name:String)
object EducationType{
  implicit val eduFmt = Json.format[EducationType]
}