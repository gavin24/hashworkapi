package domain.global.education
import play.api.libs.json.Json

case class Evaluation(id:String,name:String)
object Evaluation{
  implicit val evalFmt= Json.format[Evaluation]
}