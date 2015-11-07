val l = List(1,2,3)

object Currency extends Enumeration {
  val GBP = Value("GBP")
  val EUR = Value("EUR") //etc.
}

val res = Currency.EUR.toString