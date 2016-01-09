trait Account{
  def no:String
  def name:String
  def balance:Balance
}

case class Balance(amount:BigDecimal)

case class SavingsAccount(no:String,
                          name:String,
                          balance:Balance,
                          rateOfInterest:BigDecimal) extends Account


val calaculateIntrest:SavingsAccount=>BigDecimal={ a =>
a.balance.amount * a.rateOfInterest}

val deductTax:BigDecimal=>BigDecimal = {
  interest=>
    if(interest<1000) interest else (interest-0.1 *interest)
}

val a1 = SavingsAccount("a-001","ibm",Balance(100000),0.12)
val a2 = SavingsAccount("a-0002", "google", Balance(2000000), 0.13)
val a3 = SavingsAccount("a-0003", "chase", Balance(125000), 0.15)

val accounts = List(a1,a2,a3)

accounts map(calaculateIntrest) map(deductTax)

accounts.map(calaculateIntrest andThen deductTax)


sealed trait Curr

case object USD extends Curr
case  object AUD extends Curr
case object EUR extends Curr
case object INR extends Curr







