import play.api.libs.json.Json

case class AddressType(id:String, name:String)
object AddressType{
  implicit val addreFmt = Json.format[AddressType]
}

