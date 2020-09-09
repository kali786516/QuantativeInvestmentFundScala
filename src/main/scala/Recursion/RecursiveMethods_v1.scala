package Recursion

import PartiallyAppliedFunctionsImpForTestCasses.AllStocksData

object RecursiveMethods_v1 extends App {

  def readFinanceData():Vector[AllStocksData] = {
    val source = io.Source.fromFile("src/main/resources/GOOG.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield AllStocksData(cols(0), cols(1).toFloat,
      cols(2).toFloat, cols(3).toFloat,
      cols(4).toFloat, cols(5))
  }


   println(readFinanceData()
  .map(x => (x.date,x.close)).filter(x => x._1 == "2020-01-02")
  .groupBy(x => x._1)
  .mapValues
    (x =>
      (
        // average
        x.map(x => x._2).sum / x.map(x => 1).sum,
        // max
        x.map(x => x._2).max,
        // min
        x.map(x => 3).min,
        //sum
        x.map(x => 3).sum,
        // cumulative sum
        x.map(x => x._2).scanLeft(0)((x,y) => x + y.toInt),
        // row number
        x.map(x => 1).scanLeft(1)((x,y) => x + y)

      )
    )
   )

  def rollingAverage(numDays: Int):Unit = {

    var records = readFinanceData();

    while (records.length >= numDays) {

      val averageClose = records.map(x => x.close).take(numDays).sum / numDays

      println(s"Rolling average close for $numDays days " +
        s"date ${records.head.date}: $averageClose")

      records = records.drop(1)
    }

    println("Execution Completed !")
  }

  // rolling average of google close price for seven days
  rollingAverage(7)
}


