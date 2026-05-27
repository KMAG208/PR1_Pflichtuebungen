import java.util.Scanner;

public class Einkaufsliste {
	static Produkt first;
	
    public static void main(String[] args){
    	
    	
    //erstellt ein neues Einkaufslisten Objekt 
    	Einkaufsliste Einkaufslisteobj = new Einkaufsliste();
    	
    	// Fügt drei Produkte mit Name und Preis zur Liste hinzu
    	Einkaufslisteobj.addProduct(new Produkt("Apfel", 1.50));
    	Einkaufslisteobj.addProduct(new Produkt("Birne", 2.50));
    	Einkaufslisteobj.addProduct(new Produkt("Cola", 0.75));
  
    
    	
    	// Gibt das Produkt an Index 2 (drittes Produkt = Cola) mit Name und Preis aus
    //	System.out.println(Einkaufslisteobj.gibtProduktAnStelle(0).name+" "+Einkaufslisteobj.gibtProduktAnStelle(0).preis);
    	
    	Einkaufslisteobj.zeigeProdukte(); // Gibt alle Produkte der Liste aus
 	
    	
    }
    
    /**
     * Fügt ein neues Produkt in die Einkaufsliste hinzu.
     * @param produkt
     */
    public void addProduct(Produkt produkt) {

    		// Erstellt eine Kopie des übergebenen Produkts
        Produkt n = new Produkt(produkt.name, produkt.preis);

        // Fall 1: Liste ist leer
        if(first == null) {
            first = n; // Neues Produkt wird erstes Element der Liste
        }

        // Fall 2: Liste enthält bereits Elemente
        else {

            Produkt temp = first;// Startet am ersten Element der Liste

            // Bis zum letzten Element laufen
            while(temp.next != null) {
                temp = temp.next; // Geht zum nächsten Element
            }

            // Neues Element hinten anhängen
            temp.next = n;
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
			Produkt temp = first; // Startet am ersten Element der Liste
			
			// Läuft index-mal durch die Liste
			for(int i = 0; i < index; i++) {
				if(temp == null || temp.next == null) {// Prüft ob der Index größer als die Listenlänge ist
					return null; //Index ist zu groß
				}
				temp = temp.next;// Geht zum nächsten Element
			}
			return temp; // Gibt das Produkt an der gesuchten Stelle zurück
	}		
	
	
	/**
	 * Gibt alle Produkte der Liste aus.
	 * Jedes Produkt wird mit Name und Preis angezeigt.
	 */
	public void zeigeProdukte() {
		Produkt n = first;// Startet am ersten Element der Liste
		// Läuft solange bis das Ende der Liste erreicht ist
		while (n != null) {
		System.out.println(n.name+"  "+n.preis); // Gibt Name und Preis des aktuellen Produkts aus
		n = n.next;// Geht zum nächsten Element
		}
	}

	
}
