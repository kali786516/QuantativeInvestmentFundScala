package VarArgs

import PartiallyAppliedFunctionsImpForTestCasses.AllStocksData

object VariableArguments_v2 extends App {

  def readFinanceData():Vector[AllStocksData] = {
    val source = io.Source.fromFile("src/main/resources/stockMarketData.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield AllStocksData(cols(0), cols(1).toFloat,
      cols(2).toFloat, cols(3).toFloat,
      cols(4).toFloat, cols(5))
  }

  private val data = readFinanceData()

  def getRecords(tickers: String*):Vector[(String, String, Float)] = {
    val filteredRecords = data.filter(record => tickers.contains(record.aCompany))

    filteredRecords.map(record => (record.date, record.aCompany, record.close))
  }

  val tickers = List("TTM", "DB", "BNS")
  val records = getRecords(tickers: _*)

  records.foreach(println)
}


