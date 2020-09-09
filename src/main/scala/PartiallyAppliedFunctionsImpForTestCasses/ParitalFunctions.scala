package PartiallyAppliedFunctionsImpForTestCasses

object ParitalFunctions extends App {

  val divideBy64 = new PartialFunction[Int,Int] {

    def apply(x:Int):Int = 64/x

    override def isDefinedAt(x: Int): Boolean = x != 0

  }

  println(s"isDefined check for 11 ${divideBy64.isDefinedAt(64)}")
  println(s"isDefined check for 11 ${divideBy64.isDefinedAt(0)}")

  if (divideBy64.isDefinedAt(64)){
    divideBy64(64)
  }

  val divideBy64V2 :PartialFunction[Int,Int] = {
    case x:Int if x !=0 => 64/x
  }

}
