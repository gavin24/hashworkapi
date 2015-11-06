import play.api.libs.json.Json

case class LanguageProficiency(id:String,name:String)
object LanguageProficiency{
  implicit val lanPFmt = Json.format[LanguageProficiency]
}