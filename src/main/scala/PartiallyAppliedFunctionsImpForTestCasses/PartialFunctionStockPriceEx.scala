package PartiallyAppliedFunctionsImpForTestCasses

import FunctionalLiterals.StockRecord

object PartialFunctionStockPriceEx {

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

  val printStockRecords:PartialFunction[String,Unit] = new PartialFunction[String,Unit] {

    val recordTikker = List("MSFT","GOOG")

    def apply(ticker:String):Unit = {
      for (lines <- data.filter(x => x.aCompany == ticker)) {
        println(s"Date :- ${lines.date}")
      }
    }
    def isDefinedAt(ticker:String) = recordTikker.contains(ticker)
  }

  if(printStockRecords.isDefinedAt("MSFT")) {
    printStockRecords("MSFT")
  }

  val printStockRecordsV2:PartialFunction[String,Unit] = {
    case ticker:String if (List("MSFT","GOOGLE")).contains(ticker) =>
      for (lines <- data.filter(x => x.aCompany == ticker)) {

        println(s"${lines.date} + : + ${lines.aCompany}")
      }
  }

  val printMotorStocks:PartialFunction[String,Unit] = {
    case ticker:String if (List("TM","TSLA")).contains(ticker) =>
      for (lines <- data.filter(x => x.aCompany == ticker)) {

        println(s"${lines.date} + : + ${lines.aCompany}")
      }
  }

  List("TSLA","DB").collect(printStockRecordsV2)

  val printMotorOrTechStocks = printStockRecords orElse(printMotorStocks)


}
