import java.util.Scanner;

public class Einkaufsliste {
	static Produkt first;
	
    public static void main(String[] args) throws Exception {
    	
    	Einkaufsliste Einkaufslisteobj = new Einkaufsliste();
    	
    	Einkaufslisteobj.addProduct(new Produkt("Apfel", 1.50));
    	Einkaufslisteobj.addProduct(new Produkt("Birne", 2.50));
    	Einkaufslisteobj.addProduct(new Produkt("Cola", 0.75));
    	Einkaufslisteobj.addProduct(new Produkt("Cola", 0.75));
    	
    	System.out.println(Einkaufslisteobj.gibtProduktAnStelle(2).preis+" "+Einkaufslisteobj.gibtProduktAnStelle(2).name);
    	
    	
 	
    }
    
    /**
     * Fügt ein neues Produkt in die Einkaufsliste hinzu.
     * @param produkt
     */
	 public void addProduct(Produkt produkt) {
		Produkt n = new Produkt(produkt.name, produkt.preis);
		n.next = first;
		first = n;
	}
	
	
	/**
	 * Gibt das Produkt an einem bestimmten Index zurück.
	 * @param index Die Position des Produkts (beginnend bei 0)
	 * @return Das Produkt an der Stelle oder null bei ungültigem Index
	 */
	public Produkt gibtProduktAnStelle(int index) {
		if(index > 0) {
			Produkt temp = first;
			for(int i = 0; i< index && temp.next != null; i++) {
				Produkt nextTemp = temp.next;
				temp = nextTemp;
				
			}
			if(temp != null) {
				return temp;
			}
		}
		
		return null;
	}
	
	
	/**
	 * Gibt alle Produkte der Liste aus.
	 * Jedes Produkt wird mit Name und Preis angezeigt.
	 */
	public void zeigeProdukte() {
		Produkt n = first;
		while (n != null) {
		System.out.println(n.name+"  "+n.preis);
		n = n.next;
		}
	}

	
}
