package FunctionalLiterals

object FunctionalLiteral extends App {

  val readFinanceData = {
    val source = io.Source.fromFile("src/main/resources/GOOG.csv")

    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(x  => x.trim)
    } yield StockRecord(cols(0),
      cols(1).toFloat,
      cols(2).toFloat,
      cols(3).toFloat,
      cols(4).toFloat,
      cols(5).toFloat,
      cols(6).toDouble)
  }

  val data = readFinanceData

  def priceDelta(openPrice:Double,closePrice:Double)={
    openPrice-closePrice
  }

  val particularDateRecord = data.filter(x => x.date == "2020-01-03")

  val priceDlt = priceDelta(particularDateRecord(0).open,particularDateRecord(0).close)

  val getTotalNoRows = data.size

    val getAvgCloseValue = data.map(x => x.close).sum / data.size

  val getMinCloseValue = data.map(x => x.close).min

  val getMaxCloseValue = data.map(x => x.close).max

  def getCloseValueDate(givenData:String) = {
    val filteredClose = data.filter(x => x.date == givenData)
    filteredClose.map(x => x.close).head
  }

  val getCloseValueDt = getCloseValueDate("2020-01-03")

  println(s"Data Size :-  ${getTotalNoRows}")
  println(s"Avg Close Value :- ${getAvgCloseValue}")
  println(s"Min Close Value :- ${getMinCloseValue}")
  println(s"Max Close Value :- ${getMaxCloseValue}")
  println(s"Max Close Value :- ${getMaxCloseValue}")
  println(s"Get Close Value DT :- ${getCloseValueDt}")

  println(s"Price Delta for 2020-01-03   :- ${priceDlt}")






}
