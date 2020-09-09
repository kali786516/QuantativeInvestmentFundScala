package Recursion

import PartiallyAppliedFunctionsImpForTestCasses.AllStocksData

object RecursiveMethods_v2 extends App {

  def readFinanceData():Vector[AllStocksData] = {
    val source = io.Source.fromFile("src/main/resources/GOOG.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield AllStocksData(cols(0), cols(1).toFloat,
      cols(2).toFloat, cols(3).toFloat,
      cols(4).toFloat, cols(5))
  }

  def rollingAverage(records: Vector[AllStocksData], numDays: Int):Unit = {

    if (records.length < numDays) {
      println("Execution Completed !")
    } else {

      val averageClose = records.map(_.close).take(numDays).sum / numDays

      println(s"Rolling average close for $numDays days " +
        s"date ${records.head.date}: $averageClose")

      val updatedRecords = records.drop(1)

      rollingAverage(updatedRecords, numDays)
    }
  }

  rollingAverage(readFinanceData(), 7)
}


