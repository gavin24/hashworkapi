package conf.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Created by hashcode on 2015/11/07.
 */
object Util {
  import java.net._
  def md5Hash(text: String): String = {
    val hash = text + InetAddress.getLocalHost.getHostName
    java.security.MessageDigest.getInstance("MD5").digest(hash.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }

  def encode(name:String) ={
    new BCryptPasswordEncoder().encode(name)
  }

}
