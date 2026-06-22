/**
 * Die Klasse Uhrzeit repräsentiert eine Uhrzeit im Format HH:MM:SS,
 * bestehend aus Stunden, Minuten und Sekunden. Sie bietet Methoden zum
 * Vergleichen, Formatieren und Verschieben von Uhrzeiten (z. B. um
 * eine bestimmte Anzahl an Sekunden), wobei ein Tagesüberlauf bei
 * Bedarf korrekt behandelt wird.
 */
public class Uhrzeit {
	private int stunden;
	private int minuten;
	private int sekunden;
	
	/**
	* Konstruktor der Klasse Uhrzeit. Prüft die übergebenen Werte auf Gültigkeit.
	* Bei ungültigen Werten wird die Zeit auf 00:00:00 gesetzt.
	* @param stunden Die Stunden der Uhrzeit (0-23).
	* @param minuten Die Minuten der Uhrzeit (0-59).
	* @param sekunden Die Sekunden der Uhrzeit (0-59).
	*/
	public Uhrzeit(int stunden, int minuten, int sekunden) {
		//Prüfung ob eingegebene Werte korrekt eingegben wurden. Alle drei Werte müssen gültig sein, desshalb &&
		if(stunden >= 0 && stunden <= 23 && minuten >= 0 && minuten <= 59 && sekunden >= 0 && sekunden <= 59) {
			//Gültige Werte werden direkt übernommen
			this.sekunden = sekunden;
			this.minuten = minuten;
			this.stunden = stunden;
		} else {
			//Bei falscher Eingabe wird das Obejkt auf 00:00:00 zurückgesetzt
			this.sekunden = 0;
			this.minuten = 0;
			this.stunden = 0;
		}
	}
	
	
	/**
	* Liefert die Stunden der aktuellen Instanz.
	* @return Die Stunden der Uhrzeit.
	*/
	public int getStunden() {
		return this.stunden;
	}
	
	/**
	* Liefert die Minuten der aktuellen Instanz.
	* @return Die Minuten der Uhrzeit.
	*/
	public int getMinuten() {
		return this.minuten;
	}
	
	/**
	* Liefert die Sekunden der aktuellen Instanz.
	* @return Die Sekunden der Uhrzeit.
	*/
	public int getSekunden() {
		return this.sekunden;
	}
	
	/**
	* Vergleicht diese Uhrzeit mit einer anderen Uhrzeit auf exakte Gleichheit.
	* @param o Die zu vergleichende Uhrzeit
	* @return true bei identischen Zeitwerten, andernfalls false (auch bei null).
	*/
	public boolean equals(Uhrzeit o) {
		//Bei null eingabe wird false zurückgegeben 
		if(o == null) {
			return false;
		}
		//true nur, wenn Stunden, Minuten UND Sekunden übereinstimmen. Ansonsten false
		return this.stunden == o.stunden && this.minuten == o.minuten && this.sekunden == o.sekunden;
	}
	
	/**
	* Liefert die Uhrzeit in lesbarer, immer zweistelliger Form HH:MM:SS.
	* @return Die formatierte Uhrzeit.
	*/
	//Methode vertraut darauf, dass WWerte durch den Konstruktor schon korrekt sind
	public String toString() {
		//Leere Zeichenkette wird angelegt
		String temp = "";
		
		//Stunden zweistellig in die Zeichenkette speichern (mit führender 0 falls < 10)
		if(this.stunden< 10) {
			temp += "0"+ this.stunden;
		} else {
			temp += this.stunden;
		}
		//Zeichenkette speichert : um die Trennung zwischen Stunden und Minuten darzustellen
		temp += ":";
		
		//Minuten zweistellig in die Zeichenkette speichern (mit führender 0 falls < 10)
		if(this.minuten< 10) {
			temp += "0"+ this.minuten;
		} else {
			temp += this.minuten;
		}
		//Zeichenkette speichert : um die Trennung zwischen Minuten und Sekunden darzustellen
		temp += ":";
		
		//Sekunden zweistellig in die Zeichenkette speichern (mit führender 0 falls < 10)
		if(this.sekunden< 10) {
			temp += "0"+ this.sekunden;
		} else {
			temp += this.sekunden;
		}
		
		//Gibt die gespeicherte Zeichenkette im richtigen Format zurück
		return temp;
	}
	
	/**
	* Prüft, ob die aktuelle Uhrzeit zeitlich VOR der übergebenen Uhrzeit liegt.
	* @param o Die zu vergleichende Uhrzeit
	* @return true, wenn diese Instanz zeitlich vor der übergebenen Uhrzeit liegt, sonst
	* false.
	*/
	public boolean isBefore(Uhrzeit o) {
		//Bei null eingabe wird false zurückgegeben
		if(o == null) {
			return false;
		}
		//übergebene Stunden kleiner als aktuelle Stunden -> "nicht davor"
		if(o.stunden < this.stunden) {
			return false;
			//übergebene Stunden größer als aktuelle Stunden -> "davor"
		} else if(o.stunden > this.stunden){
			return true;
			
			//übergebene Minuten kleiner als aktuelle Minuten -> "nicht davor"
		} else if (o.minuten < this.minuten) {
			return false;
			//übergebene Minuten größer als aktuelle Minuten -> "davor"
		} else if(o.minuten > this.minuten) {
			return true;
			
			//übergebene Sekunden größer als aktuelle Sekunden -> "davor"
		} else if (o.sekunden > this.sekunden) {
			return true;
			//Ansonsten (alles gleich oder Sekunden kleiner/gleich) -> "nicht davor"
		} else {
			return false;
		}
	}
	
	/**
	* Prüft, ob die aktuelle Uhrzeit zeitlich NACH der übergebenen Uhrzeit liegt.
	* @param o Die zu vergleichende Uhrzeit
	* @return true, wenn diese Instanz zeitlich nach der übergebenen Uhrzeit liegt, sonst
	* false.
	*/
	public boolean isAfter(Uhrzeit o) {
		//Bei null eingabe wird false zurückgegeben
		if(o == null) {
			return false;
		}
		//übergebene Stunden kleiner als aktuelle Stunden -> "danach"
		if(o.stunden < this.stunden) {
			return true;
			//übergebene Stunden größer als aktuelle Stunden -> "nicht danach"
		} else if(o.stunden > this.stunden){
			return false;
			
			//übergebene Minuten kleiner als aktuelle Minuten -> "danach"
		} else if (o.minuten < this.minuten) {
			return true;
			//übergebene Minuten größer als aktuelle Minuten -> "nicht danach"
		} else if(o.minuten > this.minuten) {
			return false;
			
			//übergebene Sekunden kleiner als aktuelle Sekunden -> "danach"
		} else if (o.sekunden < this.sekunden) {
			return true;
			
			//Ansonsten (alles gleich oder Sekunden größer/gleich) -> "nicht danach"
		} else {
			return false;
		}
	}
	
	
	/**
	* Berechnet den Betrag der Differenz in Sekunden zwischen zwei Uhrzeiten.
	* Die Uhrzeiten werden als Sekunden seit 00:00:00 interpretiert.
	* Ein Tagesüberlauf wird bei dieser Methode nicht berücksichtigt.
	*
	* Beispiel: Die Differenz zwischen 23:59:00 und 00:01:00 beträgt 86280 Sekunden.
	*
	* @param o Die zweite Uhrzeit.
	* @return Die nicht negative Sekundendifferenz oder 0, falls null übergeben wurde.
	*/
	public int differenzInSekunden(Uhrzeit o) {
		//Bei null Eingabe wird 0 zurückgegeben
		if(o == null) {
			return 0;
		}
		//Aktuelle Uhrzeit in Sekunden seit 00:00:00 umrechnen
		int sekundenThis = this.stunden * 3600 + this.minuten * 60 + this.sekunden;
		
		//Übergebene Uhrzeit in Sekunden seit 00:00:00 umrechnen
		int sekundenO = o.stunden * 3600 + o.minuten * 60 + o.sekunden;
		
		//liefert den Betrag immer positiv, damit die Differenz unnabhängig davon, welche Uhrzeit größer ist, nicht negativ wird
		return Math.abs(sekundenThis - sekundenO);
	}
	
	/**
	* Erstellt ein neues Uhrzeit-Objekt, das um die angegebene Anzahl an Sekunden
	* in der Zukunft liegt. Wird 23:59:59 überschritten, beginnt die Uhrzeit wieder
	* bei 00:00:00.
	*
	* @param sekunden Die zu addierenden Sekunden.
	* @return Ein neues, verschobenes Uhrzeit-Objekt.
	*/
	public Uhrzeit addiereSekunden(int sekunden) {
		//Anzahl Minuten, die durch Sekunden-Überlauf entstehen
		int Üsek = 0; 
		//Anzahl Stunden, die durch Minuten-Überlauf entstehen 
		int Ümin = 0;
		
		//Wie viele volle Minuten ergeben sich aus aktuelle Sekunde + addierende sekunden / 60 ?
		Üsek = (this.sekunden + sekunden) / 60;
		
		//Wie viele volle Stunden ergeben sich aus aktuelle Minuten + neue Minuten / 60 ?
		Ümin = (this.minuten + Üsek) / 60;
		
		//%24 und %60 sorgen für Überlauf-Korrektur (z.B. 24h -> wieder 0h)
		return new Uhrzeit((this.stunden + Ümin)%24, (this.minuten + Üsek) %60, (this.sekunden + sekunden)%60);
		
	}
}
