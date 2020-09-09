package HigherOrderFunctions

import PartiallyAppliedFunctionsImpForTestCasses.AllStocksData

object HigherorderFunctions extends App {

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

  println(data.getClass)

  for (x <- data){
    println(x)
  }


  val records = readFinanceData

  val extractHighPrice = (records: Vector[AllStocksData]) => {
    for (record <- records) yield (record.aCompany, record.high)
  }

  val extractOpenPrice = (records: Vector[AllStocksData]) => {
    for (record <- records) yield (record.aCompany, record.open)
  }

  val extractClosePrice = (records: Vector[AllStocksData]) => {
    for (record <- records) yield (record.aCompany, record.close)
  }

  val extractPriceDelta = (records: Vector[AllStocksData]) => {
    for (record <- records) yield (record.aCompany, record.close - record.open)
  }

  val extractInfo = (records: Vector[AllStocksData],
                     extractFunction: Vector[AllStocksData] => Vector[(String, Float)]) => {
    extractFunction(records)
  }

  println("Open: " + extractOpenPrice(records))
  println("Close: " + extractClosePrice(records))
  println("Delta: " + extractPriceDelta(records))

  extractInfo(records,extractClosePrice)





}
