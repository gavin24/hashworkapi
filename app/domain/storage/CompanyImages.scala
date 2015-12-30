package domain.storage

import java.nio.ByteBuffer


/**
 * Created by hashcode on 2015/12/29.
 */
case class CompanyImages(
                        company:String,
                        id:String,
                        image:ByteBuffer,
                        filename:String,
                        mimetype:String
                          )

