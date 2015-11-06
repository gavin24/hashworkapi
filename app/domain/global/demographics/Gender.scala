import play.api.libs.json.Json

case class Gender(id:String,name:String)
object Gender{
  implicit val genderFmt = Json.format[Gender]
}