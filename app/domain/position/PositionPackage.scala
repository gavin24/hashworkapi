package domain.position

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/08.
 */
case class PositionPackage(positionId:String,
                            gradeId:String,
                            date:String,
                            state:String)
object PositionPackage{
  implicit  val posFmt = Json.format[PositionPackage]
}
