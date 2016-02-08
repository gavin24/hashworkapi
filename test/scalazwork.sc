import scalaz._
import Scalaz._
def toInts(maybeInts:List[String]): ValidationNel[Throwable,List[Int]] ={
 val validationList = maybeInts map { s =>

   Validation.fromTryCatchNonFatal(s.toInt :: Nil).toValidationNel
 }

  validationList reduce (_ +++ _)
}


