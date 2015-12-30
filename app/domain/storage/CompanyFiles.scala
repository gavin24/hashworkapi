package domain.storage

import java.nio.ByteBuffer



/**
 * Created by hashcode on 2015/12/29.
 */
case class CompanyFiles(company: String,
                        id: String,
                        file: ByteBuffer,
                        filename:String,
                        mimetype:String
                         )


