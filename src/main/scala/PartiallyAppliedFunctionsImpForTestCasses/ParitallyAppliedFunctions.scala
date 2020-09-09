package PartiallyAppliedFunctionsImpForTestCasses

object ParitallyAppliedFunctions {

  val googleStockPrices = List(1356.87,348794.78,4979747.653)

  def checkPriceGreaterThan1400(price:Double):Boolean = {
    price > 1400
  }

  def checkPriceBasedOnRange(price:Double,lower:Double,upper:Double):Boolean = {
    price >= lower && price <= upper
  }

  googleStockPrices.filter(x => checkPriceGreaterThan1400(x))

  googleStockPrices.filter(checkPriceGreaterThan1400 _)

  googleStockPrices.filter(x => checkPriceBasedOnRange(x,988498749,898))



}
