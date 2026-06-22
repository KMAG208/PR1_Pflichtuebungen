/**
 * Die Klasse Zeitpunkt repräsentiert ein Datum (Tag, Monat, Jahr) in
 * Kombination mit einer Uhrzeit im gregorianischen Kalender. Sie prüft
 * Daten auf Gültigkeit (inklusive Schaltjahrregelung), berechnet den
 * Wochentag und ermöglicht den Vergleich sowie das Verschieben von
 * Zeitpunkten um eine bestimmte Anzahl an Tagen.
 */
public class Zeitpunkt {
    private int tag;
    private int monat;
    private int jahr;
    //Atribut namens uhrzeit mit Datentyp Uhrzeit
    private Uhrzeit uhrzeit;

    /**
    * Konstruktor der Klasse Zeitpunkt.
    *
    * Prüft die übergebenen Werte auf Gültigkeit.
    * Bei einem ungültigen Datum oder bei einer Uhrzeit mit dem Wert null wird das Objekt
    * auf den Standardwert 01.01.1900 00:00:00 gesetzt.
    *
    * @param tag Der Tag des Zeitpunkts.
    * @param monat Der Monat des Zeitpunkts.
    * @param jahr Das Jahr des Zeitpunkts.
    * @param uhrzeit Die Uhrzeit des Zeitpunkts.
    */
    public Zeitpunkt(int tag, int monat, int jahr, Uhrzeit uhrzeit){
    	//Datum ist ungültig oder null -> Objekt wird auf Standardwert gesetzt
        if(uhrzeit == null || !istGueltigesDatum(tag, monat, jahr)) {
            this.tag = 1;
            this.monat = 1;
            this.jahr = 1900;
            this.uhrzeit = new Uhrzeit(0, 0, 0);
        } else {
        	//Datum ist gültig -> Werte werden direkt übernommen
            this.tag = tag;
            this.monat = monat;
            this.jahr = jahr;
            this.uhrzeit = uhrzeit;
        }
    }

    /**
    * Liefert den Tag des aktuellen Zeitpunkts.
    *
    * @return Der Tag des Zeitpunkts.
    */
    public int getTag(){
        return this.tag;
    }

    /**
    * Liefert den Monat des aktuellen Zeitpunkts.
    *
    * @return Der Monat des Zeitpunkts.
    */
    public int getMonat(){
        return this.monat;
    }

    /**
    * Liefert das Jahr des aktuellen Zeitpunkts.
    *
    * @return Das Jahr des Zeitpunkts.
    */
    public int getJahr(){
        return this.jahr;
    }

    /**
    * Liefert die Uhrzeit des aktuellen Zeitpunkts.
    *
    * @return Die gespeicherte Uhrzeit.
    */
    public Uhrzeit getUhrzeit(){
        return this.uhrzeit;
    }

    /**
    * Prüft, ob die übergebenen Werte ein gültiges Datum im gregorianischen Kalender bilden.
    *
    * Ein Datum ist genau dann gültig, wenn das Jahr größer oder gleich 1900 ist,
    * der Monat im Bereich 1 bis 12 liegt und der Tag im gültigen Bereich des jeweiligen
    * Monats liegt. Dabei werden Monatslängen und Schaltjahre berücksichtigt.
    *
    * @param tag Der zu prüfende Tag.
    * @param monat Der zu prüfende Monat.
    * @param jahr Das zu prüfende Jahr.
    * @return true, wenn das Datum gültig ist, sonst false.
    */
    public boolean istGueltigesDatum(int tag, int monat, int jahr){
    	
    	 // Monat ist nicht <= 12 UND nicht > 0 -> Datum ist ungültig
        if(!(monat <= 12 && monat > 0)){
            return false;
        }
        
        // Jahr ist nicht >= 1900 -> Datum ist ungültig
        if (!(jahr >= 1900)) {
            return false;
        }
            
    	//Tag liegt nicht im gültigen Bereich des jeweiligen Monats UND Tag ist nicht größer 0 -> Datum ist ungültig
        if (!(tag <= tageImMonat(monat, jahr) && tag > 0)) {
            return false;            
        } 
        
        //Datum erfüllt alle Gültigkeitsanforderungen -> Datum ist gültig
        return true;
    }

    /**
    * Prüft, ob das Jahr des aktuellen Zeitpunkts ein Schaltjahr ist.
    *
    * Ein Jahr ist ein Schaltjahr, wenn es durch 4 teilbar ist.
    * Ist es jedoch durch 100 teilbar, ist es kein Schaltjahr.
    * Ist es zusätzlich durch 400 teilbar, ist es doch ein Schaltjahr.
    *
    * @return true, wenn das Jahr ein Schaltjahr ist, sonst false.
    */
    public boolean istSchaltjahr(){
    	//Ist das Jahr nicht durch 100 teilbar, dann entscheidet allein die Teilbarkeit durch 4
        if(this.jahr %100 != 0){
        	// durch 4 teilbar → Schaltjahr; nicht durch 4 teilbar -> kein Schaltjahr
            return this.jahr %4 == 0;
        }
        // Durch 100 teilbar, aber auch durch 400 teilbar -> Schaltjahr
        if (this.jahr %400 != 0) {
        	// Durch 100 teilbar, aber NICHT durch 400 -> kein Schaltjahr 
            return !(this.jahr %100 == 0);            
        }
        // Durch 100 UND durch 400 teilbar -> Schaltjahr
        return this.jahr %400 == 0;
    }

    /**
    * Prüft, ob das übergebene Jahr ein Schaltjahr ist.
    *
    * Ein Jahr ist ein Schaltjahr, wenn es durch 4 teilbar ist.
    * Ist es jedoch durch 100 teilbar, ist es kein Schaltjahr.
    * Ist es zusätzlich durch 400 teilbar, ist es doch ein Schaltjahr.
    *
    * @param jahr Das Jahr welches überprüft werden soll.
    * @return true, wenn das Jahr ein Schaltjahr ist, sonst false.
    */
    public static boolean istSchaltjahr(int jahr){
        if(jahr %100 != 0){
            return jahr %4 == 0;
        }

        if (jahr %400 != 0) {
            return !(jahr %100 == 0);            
        }

        return jahr %400 == 0;
    }

    /**
    * Liefert die Anzahl der Tage des Monats des aktuellen Zeitpunkts.
    *
    * Dabei werden die unterschiedlichen Monatslängen sowie Schaltjahre
    * berücksichtigt.
    *
    * @return Die Anzahl der Tage im aktuellen Monat.
    */
    public int tageImMonat(){
    	//Standart-Monatslängen (Index 0 = Januar ...)
        int[] monatLaenge = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

        //Wenn Nicht Schaltjahr Oder Nicht Februar -> normale Monatslänge aus dem Array zurückgeben (monat -1 da Array bei 0 beginnt)
        if(!(istSchaltjahr() && this.monat == 2)){
            return monatLaenge[this.monat-1];
        }
        //Schaltjahr UND Februar -> 28Tage + 1Tag = 29Tage 
        return monatLaenge[2-1]+1;
    }

    /**
    * Liefert die Anzahl der Tage des übergebenen Monats.
    *
    * Dabei werden die unterschiedlichen Monatslängen sowie Schaltjahre
    * berücksichtigt.
    *
    * @param monat Der Monat welcher überprüft werden soll.
    * @param jahr Das Jahr in welchem dieser Monat liegt.
    * @return Die Anzahl der Tage im aktuellen Monat.
    */
    public static int tageImMonat(int monat, int jahr){
        int[] monatLaenge = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

        if(!(istSchaltjahr(jahr) && monat == 2)){
            return monatLaenge[monat-1];
        }

        return monatLaenge[2-1]+1;
    }

    /**
    * Liefert den Wochentag des aktuellen Zeitpunkts.
    *
    * Als Rückgabewert wird einer der folgenden Strings geliefert:
    * Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag oder Sonntag.
    *
    * Als Orientierung gilt: Der 01.01.1900 war ein Montag.
    *
    * @return Der Wochentag des aktuellen Zeitpunkts als String.
    */
    public String wochenTag(){
    	 // 01.01.1900 war ein Montag -> Array beginnt bei Index 0 = Montag
        String[] rueckgabeWerte = new String[]{"Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"};
        // zählt die Anzahl Tage seit dem 01.01.1900
        int tageCount = 0;

        // Zählt alle vollen Jahre zwischen 1900 und dem aktuellen Jahr,
        for(int i = 1900; i < this.jahr; i++){
        	// addiert je 366 Tage bei Schaltjahren, sonst 365 Tage
        	if(istSchaltjahr(i)){
                tageCount += 366;
            } else {
                tageCount += 365;
            }
        }
     // Zählt alle vollen Monate des aktuellen Jahres (vor dem aktuellen Monat)
        for(int i = 1; i < this.monat; i++){
        	// addiert deren jeweilige Tageanzahl
            tageCount += tageImMonat(i, this.jahr);
        }
        // Zählt die vollen Tage im aktuellen Monat (vor dem aktuellen Tag)
        for(int i = 1; i < this.tag ; i++){
            tageCount ++;
        }
     // Modulo 7 ordnet die Gesamttage einem Wochentag zu (Rest 0 = Montag, da 01.01.1900 Montag war)
        return rueckgabeWerte[(tageCount % 7)];
    }
    
    /**
    * Prüft, ob der aktuelle Zeitpunkt zeitlich vor dem übergebenen Zeitpunkt liegt.
    *
    * Dabei werden Jahr, Monat, Tag und Uhrzeit berücksichtigt.
    * Falls null übergeben wird, wird false zurückgegeben.
    *
    * @param z Der zu vergleichende Zeitpunkt.
    * @return true, wenn der aktuelle Zeitpunkt vor dem übergebenen Zeitpunkt liegt,
    * sonst false.
    */
    public boolean isBefore(Zeitpunkt z) {
    	//Bei null eingabe wird false zurückgegeben
    			if(z == null) {
    				return false;
    			}
    			//übergebens Jahr kleiner als aktuelles Jahr -> "nicht davor"
    			if(z.jahr < this.jahr) {
    				return false;
    				//übergebenes Jahr größer als aktuelles Jahr -> "davor"
    			} else if(z.jahr > this.jahr){
    				return true;
    				
    				//übergebener Monat kleiner als aktueller Monat -> "nicht davor"
    			} else if (z.monat < this.monat) {
    				return false;
    				//übergebener Monat größer als aktueller Monat -> "davor"
    			} else if(z.monat > this.monat) {
    				return true;
    				
    				//übergebener Tag kleiner als aktueller Tag -> "nicht davor"
    			} else if (z.tag < this.tag) {
    				return false;
    				//Übergebener Tag größer als aktueller Tag -> "davor"
    			} else if(z.tag > this.tag){
    				return true;
    			
    			//Jahr, Monat UND Tag sind gleich -> die Uhrzeit entscheidet
    			}else{
    				return this.uhrzeit.isBefore(z.uhrzeit);
    			}
    }
    
    /**
    * Prüft, ob der aktuelle Zeitpunkt zeitlich nach dem übergebenen Zeitpunkt liegt.
    *
    * Dabei werden Jahr, Monat, Tag und Uhrzeit berücksichtigt.
    * Falls null übergeben wird, wird false zurückgegeben.
    *
    * @param z Der zu vergleichende Zeitpunkt.
    * @return true, wenn der aktuelle Zeitpunkt nach dem übergebenen Zeitpunkt liegt,
    * sonst false.
    */
    public boolean isAfter(Zeitpunkt z) {
    	//Bei null eingabe wird false zurückgegeben
    			if(z == null) {
    				return false;
    			}
    			//übergebenes Jahr kleiner als aktuelles Jahr -> "danach"
    			if(z.jahr < this.jahr) {
    				return true;
    				//übergebenes Jahr größer als aktuelles Jahr -> "nicht danach"
    			} else if(z.jahr > this.jahr){
    				return false;
    				
    				//übergebener Monat kleiner als aktueller Monat -> "danach"
    			} else if (z.monat < this.monat) {
    				return true;
    				//übergebener Monat größer als aktueller Monat -> "nicht danach"
    			} else if(z.monat > this.monat) {
    				return false;
    				
    				//übergebener Tag kleiner als aktueller Tag -> "danach"
    			} else if (z.tag < this.tag) {
    				return true;
    			
    				//Übergebener Tag größer als aktueller Tag -> "nicht danach"
    			}else if(z.tag > this.tag) {
    				return false;
    						
    				//Jahr, Monat UND Tag sind gleich -> die Uhrzeit entscheidet
    			}else {
    				return this.uhrzeit.isAfter(z.uhrzeit);
    			}
    }
    
    /**
    * Vergleicht den aktuellen Zeitpunkt mit einem anderen Zeitpunkt auf exakte
    Gleichheit.
    *
    * Zwei Zeitpunkt-Objekte gelten genau dann als gleich, wenn Tag, Monat, Jahr
    * und Uhrzeit übereinstimmen.
    * Falls null übergeben wird, wird false zurückgegeben.
    *
    * @param z Der zu vergleichende Zeitpunkt.
    * @return true bei identischen Datums- und Uhrzeitwerten, sonst false.
    */
    public boolean equals(Zeitpunkt z) {
    	//Bei null eingabe wird false zurückgegeben 
    	if(z == null) {
    	return false;
    	}
    	//true nur, wenn Tag, Monat, Jahr UND Uhrzeit übereinstimmen. Ansonsten false
    	return this.tag == z.tag && this.monat == z.monat && this.jahr == z.jahr && this.uhrzeit.equals(z.uhrzeit);
    }
    
    
    /**
    * Liefert den Zeitpunkt mit Datum und Uhrzeit in lesbarer Form zurück.
    *
    * @return Der formatierte Zeitpunkt mit Datum und Uhrzeit.
    */
    public String toString() {

		//Leere Zeichenkette wird angelegt
		String temp = "";
		
		//Tag zweistellig in die Zeichenkette speichern (mit führender 0 falls < 10)
		if(this.tag< 10) {
			temp += "0"+ this.tag;
		} else {
			temp += this.tag;
		}
		//Zeichenkette speichert . um die Trennung zwischen Tag und Monat darzustellen
		temp += ".";
		
		//Monat zweistellig in die Zeichenkette speichern (mit führender 0 falls < 10)
		if(this.monat< 10) {
			temp += "0"+ this.monat;
		} else {
			temp += this.monat;
		}
		//Zeichenkette speichert . um die Trennung zwischen Monat und Jahr darzustellen
		temp += ".";
		
	    // Jahr (immer 4-stellig, da Jahre ab 1900 ohnehin 4 Stellen haben)
	    temp += this.jahr;

	    // Leerzeichen zur Trennung zwischen Datum und Uhrzeit
	    temp += " ";

	    // Uhrzeit wird über die bereits fertige toString()-Methode der Klasse Uhrzeit geholt
	    temp += this.uhrzeit.toString();


		//Gibt die gespeicherte Zeichenkette im richtigen Format zurück
		return temp;	
    }
    
    /**
    * Erstellt ein neues Zeitpunkt-Objekt, das um die angegebene Anzahl an Tagen
    * in der Zukunft liegt.
    *
    * Die Uhrzeit bleibt unverändert. Monatswechsel, Jahreswechsel und Schaltjahre
    * müssen korrekt berücksichtigt werden.
    * Der Parameter tage ist immer größer oder gleich 0.
    *
    * @param tage Die Anzahl der zu addierenden Tage.
    * @return Ein neues, verschobenes Zeitpunkt-Objekt.
    */
    public Zeitpunkt addiereTage(int tage) {
    	
        // Lokale Kopien von Tag, Monat, Jahr, die schrittweise erhöht werden
    	int neuerTag = this.tag;
    	int neuerMonat = this.monat;
    	int neuesJahr = this.jahr;
    	
    	// Schleife läuft genau "tage" mal, ein Tag pro Durchlauf
    	for(int i = 0; i < tage; i++) {
    		neuerTag = neuerTag + 1;
    	
            // Tag überschreitet die Monatslänge -> zurück auf 1, Monat um 1 erhöhen
    		if(neuerTag > tageImMonat(neuerMonat, neuesJahr)) {
    		neuerTag = 1;
    		neuerMonat = neuerMonat + 1;
    		}
    		
    		// Monat überschreitet 12 -> zurück auf Januar, Jahr um 1 erhöhen
    		if(neuerMonat > 12) {
    		neuerMonat = 1;
    		neuesJahr = neuesJahr + 1;
    		}
    	}
    	// Neues Zeitpunkt-Objekt mit verschobenem Datum, Uhrzeit bleibt unverändert
    	return new Zeitpunkt(neuerTag, neuerMonat, neuesJahr, this.uhrzeit);
    }
}















