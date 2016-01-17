
import scalaz._, Scalaz._
import scalaz.Kleisli._



val x:Option[Int] = 2.some // scalaz enrichment for options
val y:Option[Int] = 3.some
val z:Option[Int] = 5.some

(x |@| y) {_+_}

