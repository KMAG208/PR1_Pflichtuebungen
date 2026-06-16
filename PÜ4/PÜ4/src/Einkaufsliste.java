public class Einkaufsliste {
	Produkt[] Einkaufsliste = new Produkt[10];
	
    public static void main(String[] args){
    	
    	
    //erstellt ein neues Einkaufslisten Objekt 
    	Einkaufsliste Einkaufslisteobj = new Einkaufsliste();
    	

		//Tests
    	Einkaufslisteobj.addProduct(new Produkt("Apfel", 1.00));
    	Einkaufslisteobj.addProduct(new Produkt("Banane", 2.00));
    	Einkaufslisteobj.addProduct(new Produkt("Birne", 3.00));
		Einkaufslisteobj.addProduct(new Produkt("Banane", 4.00));
		
		Einkaufslisteobj.gibtProduktAnStelle(5);
    	
    }
    
    /**
     * Fügt ein neues Produkt in die Einkaufsliste hinzu.
     * @param produkt
     */
    public void addProduct(Produkt produkt) {
    	for(int i = 0; i< Einkaufsliste.length; i++) {
    		if(Einkaufsliste[i] == null) {
    			Einkaufsliste[i] = produkt;
    		}
    	}
    }
	
	/**
	 * Gibt das Produkt an einem bestimmten Index zurück.
	 * @param index Die Position des Produkts (beginnend bei 0)
	 * @return Das Produkt an der Stelle oder null bei ungültigem Index
	 */
	public Produkt gibtProduktAnStelle(int index) {
		if(index < 0) {  
			return null; // Negativer Index ist ungültig, gibt null zurück
		}
		
		if(index > Einkaufsliste.length) {
			return null;
		}
		
		return Einkaufsliste[index];
	}		
	
	
	/**
	 * Gibt alle Produkte der Liste aus.
	 * Jedes Produkt wird mit Name und Preis angezeigt.
	 */
	public void zeigeProdukte() {
		for(int i = 0; i < Einkaufsliste.length; i++) {
			System.out.println(Einkaufsliste[i].name+" "+Einkaufsliste[i].preis);
		}
	}
	
	/**
	 * Entfernt das erste Vorkommen eines Produkts anhand seines Namens.
	 * @param produktName Name des zu entfernenden Produkts
	 */
	public void entferneProdukt(String produktName) {
		int tempI = 0;
		for(int i = 0; i < Einkaufsliste.length; i++) {
			if(Einkaufsliste[i].name == produktName) {
				tempI = i;
				Einkaufsliste[i]= null;
			}
		}
		
		Produkt temp;
		
		for(int i = Einkaufsliste.length-1; i > tempI; i--) {
			temp = Einkaufsliste[i];
			Einkaufsliste[i-1] = temp;
		}
	}
	
	/**
	 * Findet zwei Produkte, deren Preise zusammen dem Budget entsprechen.
	 * @param budget Zielwert
	 * @return Zwei passende Produkte oder null, falls keine Kombination existiert
	 */
	public Produkt[] findeProduktKombination(double budget) {
		
		//Geht die Produkte durch
		for(int i = 0; i < Einkaufsliste.length -1; i++) {
			for(int j = 1; j < Einkaufsliste.length; j++) {
				//Überprüfung ob beide produkte identisch sind
				if(Einkaufsliste[i]!= Einkaufsliste[j]) {
					//Überprüfung ob der Preis der gewünschte Preis ist
					if(Einkaufsliste[i].getPreis() + Einkaufsliste[j].getPreis() == budget) {
						//Speichern und Rückgeben der Produkte
						Produkt[] Produktkombination = new Produkt [2];
						Produktkombination[0] = Einkaufsliste[i];
						Produktkombination[1] = Einkaufsliste[j];
						return Produktkombination;
					}
				}
			}
		}
		//falls kein Produkt gefunden wurde
		return null;
	}
}

