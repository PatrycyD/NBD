object Zadanie1 {
  def main(args: Array[String]): Unit = {
    //ZADANIE 1
    println("\n---ZADANIE 1")
    val weekdays = List("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")
    println(weekdays)

    // 1A
    println("\n---1A")
    var alltogether = ""

    for (day <- weekdays) {
      alltogether = alltogether + ", " + day
    }

    alltogether = alltogether.stripPrefix(", ")
    println(alltogether)

    //  1B
    println("\n---1B")
    var all_together = ""

    for (day <- weekdays) {
        if(day.startsWith("P")){
      all_together = all_together + ", " + day
      }
    }

    all_together = all_together.stripPrefix(", ")
    println(all_together)

    // 1C
    println("\n---1C")
    var together = ""
    var i = 0

    while (i < weekdays.length){
      together = together + ", " + weekdays(i)
      i += 1
    }

    together = together.stripPrefix(", ")
    println(together)


    //ZADANIE 2
    println("\n---ZADANIE 2")
    println("\n---2A")

  def recurrent(weekdays:List[String], current_string:String):String = {

      var days = weekdays
      val new_string = current_string + ", " + days(0)

      days = days.takeRight(days.length - 1)
      if (days.length > 0) recurrent(days, new_string) else return new_string.stripPrefix(", ").stripSuffix(", ")
  }
    println(recurrent(weekdays, ""))

    //2B
    println("\n---2B")
  def backward_recurrent(weekdays:List[String], current_string:String):String = {

      var days = weekdays
      val new_string = current_string + ", " + days.last

      days = days.dropRight(1)

      if (days.length > 0) backward_recurrent(days, new_string) else return new_string.stripPrefix(", ").stripSuffix(", ")
  }
    println(backward_recurrent(weekdays, ""))

  //ZADANIE 3
  println("\n---ZADANIE 3")
  import scala.annotation.tailrec

  def tail_recursion(weekdays:List[String]): String = { 
        @tailrec 
        def iter(current:String, weekdays:List[String]): String = { 
            if (weekdays.length == 0)current else iter(current.stripPrefix(", ") + ", " + weekdays(0), weekdays.takeRight(weekdays.length-1)) 
             }
           iter("", weekdays)
        } 
        println(tail_recursion(weekdays))

  //ZADANIE 4
  println("\n---ZADANIE 4")

  //4A
  println("\n---4A")
  def perform_foldl(weekdays:List[String]): String = {
    weekdays.foldLeft("") (_ + ", " + _).stripPrefix(", ")
  }
  println(perform_foldl(weekdays))

  //4B 
  println("\n---4B")
  def perform_foldr(weekdays:List[String]): String = {
    weekdays.foldRight("") (_ + ", " + _).stripSuffix(", ")
  }
  println(perform_foldr(weekdays))

  //4C
  println("\n---4C")
  def perform_selective_fold(weekdays:List[String]): String = {
    weekdays.foldLeft("") {
      (day, result) => if (result.startsWith("P")) result + ", " + day else day}.stripSuffix(", ")
  }
  println(perform_selective_fold(weekdays))

  //ZADANIE 5
  println("\n---ZADANIE 5")
  var products = Map("samochod" -> 10000, "rower" -> 500, "hulajnoga" -> 100)
  println(f"Unmodified map: ${products}")

  val modified_prods = products.map({case(product, price) => product -> price * 0.9})
  println(f"Modified map: ${modified_prods}")

  //ZADANIE 6
  println("\n---ZADANIE 6")

  def print_tuple(tup: (String, Int, Boolean)): Unit = {
    println(tup._1 + ", " + tup._2 + ", " + tup._3)
  }

  val example_tup:(String, Int, Boolean) = ("string", 100, true)
  println(print_tuple(example_tup))

  //ZADANIE 7
  println("\n---ZADANIE 7")
  val rower_finder: Option[Int] = products.get("rower")

  println(f"Cena roweru: ${rower_finder.get}")

  //ZADANIE 8
  println("\n---ZADANAIE 8")

  def no_0_list(ints: List[Int]): List[Int] = {
    @tailrec
    def iter(current: List[Int], left: List[Int]): List[Int] = current match{
      case Nil => left
      case first::rest if (first == 0) => iter(rest, left)
      case first::rest => iter(rest, (left :+ first))
    }
    iter(ints, Nil)
  }

  val example_list: List[Int] = List(5, 0, -5, 1, -11, 3, 0, 4, 17, 100, 2, 0, 0, -1, 4, 0)
  println(no_0_list(example_list))

  //ZADANIE 9
  println("\n---ZADANIE 9")

  def add_one(ints: List[Int]): List[Int] = {
    return ints.map(x => x + 1)
  }
  println(add_one(example_list))

  //ZADANIE 10
  println("\n---ZADANIE 10")

  def return_range(values: List[Double]): List[Double] = {
    return values.filter(x => (x >= -5 && x <= 12))
  }

  val values = List(-100, 100, 2/3, 0.654, 10, -4, scala.math.sqrt(555), -27, scala.math.Pi,
    0.667, 7-1/10, 5+17/23, scala.math.sqrt(6), -27/87, -5, -5.00001, 12, 12.0001)

  println(return_range(values))
  }
}
