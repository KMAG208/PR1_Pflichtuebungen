public class Produkt {
	 // Name des Produkts, Standardwert "Unknown" falls kein Name angegeben
	String name = "Unknown"; 
	
	// Preis des Produkts, Standardwert 0.0 falls kein Preis angegeben
	double preis = 0.0;
	
	// Zeiger auf das nächste Produkt in der verketteten Liste, null = kein Nachfolger
	Produkt next = null;
	
	
	// Konstruktor, setzt Name und Preis des Produkts
	Produkt(String eName, double ePreis){
		// Prüft ob der übergebene Name gültig ist (nicht null)
		if(eName != null) {
			// Setzt den Namen auf den übergebenen Wert
			name = eName;
		}
		// Prüft ob der übergebene Preis gültig ist (nicht negativ)
		if(ePreis >= 0) {
			// Setzt den Preis auf den übergebenen Wert
			preis = ePreis;
		}
	}
	
	/**
	 * Reduziert den Preis um den angegebenen Proentsatz.
	 * @param prozentsatz 
	 */
	public void gebeRabatt(double prozentsatz) {
		if(prozentsatz >=0 && prozentsatz <= 100) {
			preis = preis- prozentsatz;	
		}
	}
	
	/**
	 * gibt den Namen des Produktes zurück
	 * @return Name des Produktes
	 */
	public String getName() {
			return name;
		}
	
	/**
	 * gibt den Preis des Produktes zurück
	 * @return Preis des Produktes
	 */
	public double getPreis() {
		return preis;
	}
}
