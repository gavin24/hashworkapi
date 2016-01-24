val num = BigDecimal(10.0)

val range = num.to(BigDecimal(15.0),BigDecimal(1.5))

range foreach(value=> println(" Number ",value,"-" , range.indexOf(value)))