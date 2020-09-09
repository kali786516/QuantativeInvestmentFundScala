package NestedMethods

import PartiallyAppliedFunctionsImpForTestCasses.AllStocksData

object NestedMethods extends App {

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

  def printRecords(ticker:String):Unit = {

    println("Date       | Ticker | Close")

    def printRecords():Unit = {
      for (row <- data.filter(_.aCompany == ticker)) {

        println(s"${row.date} | ${row.aCompany}    | ${row.close}")
      }
    }

    printRecords()
  }

  printRecords("BNS")
}


