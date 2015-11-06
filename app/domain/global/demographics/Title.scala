package domain.global.demographics

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/11/04.
 */
case class Title(id:String, name:String)

object Title{
 implicit val titleFmt = Json.format[Title]
}
