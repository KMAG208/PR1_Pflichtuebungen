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
		if(stunden >= 0 && stunden <= 23 && minuten >= 0 && minuten <= 60 && sekunden >= 0 && sekunden <= 60) {
			this.sekunden = sekunden;
			this.minuten = minuten;
			this.stunden = stunden;
		} else {
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
		if(o == null) {
			return false;
		}
		return this.stunden == o.stunden && this.minuten == o.minuten && this.sekunden == o.sekunden;
	}
	
	/**
	* Liefert die Uhrzeit in lesbarer, immer zweistelliger Form HH:MM:SS.
	* @return Die formatierte Uhrzeit.
	*/
	public String toString() {
		String temp = "";
		
		if(this.stunden< 10) {
			temp += "0"+ this.stunden;
		} else {
			temp += this.stunden;
		}
		
		temp += ":";
		
		if(this.minuten< 10) {
			temp += "0"+ this.minuten;
		} else {
			temp += this.minuten;
		}
		
		temp += ":";
		
		if(this.sekunden< 10) {
			temp += "0"+ this.sekunden;
		} else {
			temp += this.sekunden;
		}
		
		return temp;
	}
	
	/**
	* Prüft, ob die aktuelle Uhrzeit zeitlich VOR der übergebenen Uhrzeit liegt.
	* @param o Die zu vergleichende Uhrzeit
	* @return true, wenn diese Instanz zeitlich vor der übergebenen Uhrzeit liegt, sonst
	* false.
	*/
	public boolean isBefore(Uhrzeit o) {
		if(o == null) {
			return false;
		}
		
		if(o.stunden < this.stunden) {
			return false;
		} else if(o.stunden > this.stunden){
			return true;
		} else if (o.minuten < this.minuten) {
			return false;
		} else if(o.minuten > this.minuten) {
			return true;
		} else if (o.sekunden > this.sekunden) {
			return true;
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
		if(o == null) {
			return false;
		}
		
		if(o.stunden < this.stunden) {
			return true;
		} else if(o.stunden > this.stunden){
			return false;
		} else if (o.minuten < this.minuten) {
			return true;
		} else if(o.minuten > this.minuten) {
			return false;
		} else if (o.sekunden < this.sekunden) {
			return true;
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
		if(o == null) {
			return 0;
		}
		
		int[] Werte = new int[]{3600, 60, 1};
		int[] WerteThis = new int[] {this.stunden, this.minuten, this.sekunden};
		int[] WerteO = new int[] {o.stunden, o.minuten, o.sekunden};
		int sekO = 0;
		int sekThis = 0;
		
		
		for(int i = 0; i < Werte.length; i++) {
			for(int j = 0; j < WerteThis[i]; j++) {
				sekThis += Werte[i];
			}
			
			for(int k = 0; k < WerteO[i]; k++) {
				sekO += Werte[i];
			}
		}
		
		return Math.abs(sekThis - sekO);
		
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
		int Üsek = 0;
		int Ümin = 0;
		
		Üsek = (this.sekunden + sekunden) / 60;
		Ümin = (this.minuten + Üsek) / 60;
		
		return new Uhrzeit((this.stunden + Ümin)%24, (this.minuten + Üsek) %60, (this.sekunden + sekunden)%60);
		
	}
}
