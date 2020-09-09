package HigherOrderFunctions

import PartiallyAppliedFunctionsImpForTestCasses.AllStocksData

object HigherOrderFunctionV2 extends App {

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

  val data = readFinanceData

  def getStockDetails(ticker: String)(extractFunction: (AllStocksData) => Float): Float = {
    val filteredRecords = data.filter(_.aCompany == ticker)
    assert(filteredRecords.length == 1)

    extractFunction(filteredRecords(0))
  }

  val ttmStockDetails = getStockDetails("TTM") {
    (record: AllStocksData) => record.high
  }
  println("---------------TTM high")
  println(ttmStockDetails)

  val googStockDetails = getStockDetails("GOOG") {
    (record: AllStocksData) => record.low
  }
  println("---------------GOOG low")
  println(googStockDetails)



}
