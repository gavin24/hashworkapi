import play.api.libs.json.Json

case class IdentificationType(id: String, name: String)
object IdentificationType {
  implicit val idFmt = Json.format[IdentificationType]
}