package domain.job

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/07.
 */
case class Job(
              compnay:String,
              id:String,
              jobClassificationId:String,
              title:String,
              code:String,
              description:String,
              state:String
                )

object Job {

  implicit val jobFmt = Json.format[Job]

}
