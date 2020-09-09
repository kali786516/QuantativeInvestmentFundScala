package ExpressionBlock

import PartiallyAppliedFunctionsImpForTestCasses.AllStocksData

object ExpressionBlock extends App {

  val readFinanceData = {
    val source = io.Source.fromFile("src/main/resources/stockMarketData.csv")

    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(x  => x.trim)
    } yield AllStocksData(cols(0),
      cols(1).toFloat,
      cols(2).toFloat,
      cols(3).toFloat,
      cols(4).toFloat,
      cols(5).toString)
  }

  val records = readFinanceData

  val getStockDetails = (ticker: String) => records.filter(_.aCompany == ticker)

  val ttmStockDetails = getStockDetails("TTM")
  println("---------------TTM")
  println(ttmStockDetails)

  val randomStockDetails = getStockDetails {
    val list = List("MSFT", "GOOG", "TM", "TTM", "DB", "BNS")
    val randomNumber = util.Random.nextInt(list.length)
    // this the value what we are passing to function getStockDetails
    list(randomNumber)
  }

  println("---------------random stock")
  println(randomStockDetails)




}
