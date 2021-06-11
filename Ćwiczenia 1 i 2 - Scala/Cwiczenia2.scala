object Cwiczenia2 {
	def main(args: Array[String]): Unit = {

		//ZADANIE 1
		println("\n---ZADANIE 1")
		def patternMatching(day: String): String = day match {
			case "Poniedzialek" => "Praca"
			case "Wtorek" => "Praca"
			case "Sroda" => "Praca"
			case "Czwartek" => "Praca"
			case "Piatek" => "Praca"
			case "Sobota" => "Weekend"
			case "Niedziela" => "Weekend"
			case _ => "Nie ma takiego dnia"
		}

		println(f"Poniedzialek - ${patternMatching("Poniedzialek")}")
		println(f"Sobota - ${patternMatching("Sobota")}")
		println(f"понедельник - ${patternMatching("понедельник")}")

		//ZADANIE  2
		println("\n---ZADANIE 2")
		class KontoBankowe(var stanPoczatkowy: Double = 0) {
			private var stan: Double = stanPoczatkowy

			def stanKonta: Double = this.stan

			def wplata(kwota: Double): Double = {
				if (kwota > 0) { this.stan += kwota 
				this.stan
			}else{
				throw new Error("Kwota wplaty musi byc wieksza od 0")
			}
		}

			def wyplata(kwota: Double): Double = {
				if (kwota <= stanKonta && kwota > 0) { 
					this.stan -= kwota
					this.stan
				}else{
					throw new Error("Kwota wplaty jest nieprawidlowa lub jest wieksza od stanu konta")
				}
			}

		}

	val konto1 = new KontoBankowe()
	println(f"Konto z domyslna wartoscia 0: ${konto1.stanKonta}")

	val konto2 = new KontoBankowe(1050)
	println(f"Poczatkowy stan konta bez domyslnej wartosci: ${konto2.stanKonta}")

	val kwotaWplaty: Double = 307.5
	konto2.wplata(kwotaWplaty)
	println(f"Wplata: ${kwotaWplaty}. Stan konta po wplacie: ${konto2.stanKonta}")

	val kwotaWyplaty: Double = 157.5
	konto2.wyplata(kwotaWyplaty)
	println(f"Wyplata: ${kwotaWyplaty}. Stan konta po wyplacie: ${konto2.stanKonta}")


	//ZADANIE 3
	println("\n---ZADANIE 3")

	case class Osoba(val imie: String, val nazwisko: String) 

	def przywitanie(osoba: Osoba): String = osoba match {
			case Osoba("Adam", "Malysz") => "Dzien dobry Panie Adamie!"
			case Osoba("Janina", "Nowak") => "Dzien dobry Pani Janino!"
			case Osoba("Krzysztof", "Kononowicz") => "Uszanowanie Panie Krzysztofie!"
			case _ => "Dzien dobry nieznajomy!" 
		}

	val malysz = new Osoba("Adam", "Malysz")
	val nowak = new Osoba("Janina", "Nowak")
	val kononowicz = new Osoba("Krzysztof", "Kononowicz")
	val suchodolski = new Osoba("Major", "Suchodolski")

	println(f"Przywitanie ${malysz.imie} ${malysz.nazwisko}: ${przywitanie(malysz)}")
	println(f"Przywitanie ${nowak.imie} ${nowak.nazwisko}: ${przywitanie(nowak)}")
	println(f"Przywitanie ${kononowicz.imie} ${kononowicz.nazwisko}: ${przywitanie(kononowicz)}")
	println(f"Przywitanie ${suchodolski.imie} ${suchodolski.nazwisko}: ${przywitanie(suchodolski)}")

	//ZADANIE 4
	println("\n---ZADANIE 4")

	def trzykrotneDzialanie(num: Double, func: (Double) => Double): Double = {
		return func(func(func(num)))
	}

	def doKwadratu(num: Double): Double = {
		return scala.math.pow(num, 2)
	}

	def dodajDziesiec(num: Double): Double = {
		return num + 10
	}

	def pomnozPrzezTrzy(num: Double): Double = {
		return num * 3
	}

	println(f"Trzykrotne podniesienie do drugiej potegi ${trzykrotneDzialanie(2, doKwadratu)}")
	println(f"Trzykrotne dodanie dziesiec ${trzykrotneDzialanie(2, dodajDziesiec)}")
	println(f"Trzykrotne pomnozenie przez trzy ${trzykrotneDzialanie(2, pomnozPrzezTrzy)}")

	//ZADANIE 5
	println("\n---ZADANIE 5")

	class Osoba2 (val imie: String, val nazwisko: String, var podatek: Double)

	trait Student extends Osoba2 {
		podatek = 0
	}

  trait Pracownik extends Osoba2 {

    val podatek_procent: Double = 0.2
    private var pensja: Double = 0
    set_podatek()

    def set_pensja(wysokosc_pensji: Double): Unit = {
    	pensja = wysokosc_pensji
      set_podatek()
    }

    def get_pensja(): Double = {
      return pensja
    }

    def set_podatek(): Unit = {
      podatek = podatek_procent * pensja
    }
  }

  trait Nauczyciel extends Pracownik {
  	override val podatek_procent = 0.1
  }

  val maciejowski = new Osoba2("Maciej", "Maciejowski", 100)
  val kowalski = new Osoba2("Jan", "Kowalski", 0) with Pracownik
	val kowalska = new Osoba2("Anna", "Kowalska", 100) with Student
	val kowalewska = new Osoba2("Joanna", "Kowalewska", 0) with Pracownik with Student
	val nowakowna = new Osoba2("Jaroslawa", "Nowakowna", 0) with Student with Pracownik
	val dworakowski = new Osoba2("Patrycy", "Dworakowski", 0) with Nauczyciel
  
	println(f"Podatek Maciejowskiego (Osoba): ${maciejowski.podatek}")

  kowalski.set_pensja(1000)
  println(f"\nPensja Kowalskiego (Pracownik): ${kowalski.get_pensja()}")
  println(f"Podatek Kowalskiego (Pracownik): ${kowalski.podatek}")

  println(f"\nPodatek Kowalskiej (Student): ${kowalska.podatek}")

  kowalewska.set_pensja(1000)
  println(f"\nPensja Kowalewskiej (Pracownik, Student): ${kowalewska.get_pensja()}")
  println(f"Podatek Kowalewskiej (Pracownik, Student): ${kowalewska.podatek}")

  nowakowna.set_pensja(1000)
  println(f"\nPensja Nowakowny (Student, Pracownik): ${nowakowna.get_pensja()}")
  println(f"Podatek Nowakowny (Student, Pracownik): ${nowakowna.podatek}")
  //W obu przypadkach, gdy wystepuje pracownik razem ze studentem 
  //brny jest podatek dwudziestoprocentowy. Wynika to z tego, ze podatek jest obliczany
  //na podstawie pensji, ktora jest wlasnoscia tylko u pracownika, nie u Osoby (czyli tez studenta)

  dworakowski.set_pensja(1000)
  println(f"\nPensja Dworakowskiego (Nauczyciel): ${dworakowski.get_pensja()}")
  println(f"Podatek Dworakowskiego (Nauczyciel): ${dworakowski.podatek}")
	}
}