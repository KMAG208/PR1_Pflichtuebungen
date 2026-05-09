package Pflichtübung_3;

import java.util.Scanner;

public class Snackautomat {
    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);
        
        //Array von Array zur Speicherung der Produktnamen in 3 Kategorien
        String[][] produkte = {
            {"Wasser", "Cola", "Saft", "Kaffee"},
            {"Chips", "Schokolade", "Nuesse", "Muesliriegel"},
            {"Apfel", "Banane", "Trauben", "Birne", "Mandarine"}
        };

        //Array von Array für die Preise in Cent
        int[][] preise = {
            {100, 150, 120, 185},
            {199, 180, 225, 165},
            {85, 92, 111, 95, 89}
        };

        int eProdukt;  //Speichert die vom Benutzer gewählte Produktnummer 
        String eKategorie; //Speichert die gwählte Kategorie (G, S oder O)

        //Aufruf der Methode, um das Menü auf der Konsole anzuzeigen
        zeigeProdukte(produkte);


        System.out.println("Geben Sie die Kategorie ein (G, S, O): ");
        eKategorie = in.nextLine();

        System.out.println("Geben Sie das Produkt ein: ");
        eProdukt = in.nextInt();

        //Bestimmung des Preises über printf und Ausgabe mithilfe Division durch 100 in Euro 
        System.out.printf("Preis: %.2f €%n", getPreis(preise, eKategorie, eProdukt) / 100.0);

        
      //Aufforderung zur Geldeingabe 
        System.out.println("Geld einwerfen (in Euro, zb. 2,50):");
        double eingabeEuro = in.nextDouble();
        
        //Wandelt Euro in Cent um und rundet korrekt auf ganze Cent
        int geldCent = (int) Math.round(eingabeEuro * 100);
        
       
        //Führt den Kauf durch und berechnet das Rückgeld in Münzen
        int[] rueckgeldMuenzen = kaufeProdukt(getPreis(preise, eKategorie, eProdukt), geldCent);
       
        
        System.out.println("Rückgeld:");
        
        //Array der möglichen Münzwerte in Cent
        int[] muenzWerte = {200, 100, 50, 20, 10, 5, 2, 1};

        //Schleife geht alle Münztypen nacheinander durch
        for (int i = 0; i < rueckgeldMuenzen.length; i++) {
        	//Gibt den Münzwert in Euro und die Anzahl dieser Münzen aus
            System.out.printf("%.2f €: %d%n", muenzWerte[i] / 100.0, rueckgeldMuenzen[i]);
        }
    }


    /**
     * Funktion, die die Kategorien des Snackautomaten zurückgibt.
     * @return Ein Array von Strings, das die Kategorien des Snackautomaten enthält.
     */
    public static String[] getKategorien(){
        String[] kategorien = {"G - Getränke", "S - Snacks", "O - Obst"};
        return kategorien;
    }

    /**
     * Gibt alle Produkte nach Kategorien geordnet aus.
     * Jede Kategorie wird mit ihren jeweiligen Produkten
     * in einer neuen Zeile dargestellt.
     * Jedes Produkt wird mit seiner passenden Nummer angezeigt.
     * @param produkte Array von Arrays mit Produktnamen
     */
    public static void zeigeProdukte(String[][] produkte){
    	//Äußere Schleife geht durch die 3 Kategorien
        for(int i = 0; i < getKategorien().length; i++){

        	//Öffnende Klammer am Anfang der Zeile
            System.out.print(getKategorien()[i]+ " [ ");
            //Innere Schleife geht durch jedes Produkt in der aktuellen Kategorie
            for(int j = 0; j < produkte[i].length; j++){
            	// Gibt Produktnummer und Produktname aus. j+1 damit der Nutzer Nummern ab 1 sieht
                System.out.print(j+1+ ": "+ produkte[i][j]+ " ");
            }
            //Schließende Klammer am Ende der Zeile 
            System.out.println("]");
        }
    }

    /**
 * Gibt den Preis eines ausgewählten Produkts zurück.
 * @param preise Array von Arrays mit Preisen in Cent
 * @param kategorie Auswahl der Kategorie als String ("G", "S", "O")
 * @param produkt Auswahl des Produkts innerhalb der gewählten Kategorie
 * @return Preis in Cent
 */
public static int getPreis(int[][] preise, String kategorie, int produkt) {
	//switch ordnet die Buchstaben-Eingabe dem richtigen Array-Index zu
     switch (kategorie) {
        case "G": return preise[0][produkt - 1]; 
        case "S" : return preise[1][produkt - 1];
        case "O" : return preise[2][produkt - 1];
       //Falls ungültige Kategorie 
        default: return 0;
    }
}

/**
 * Führt den Kauf eines Produkts durch und berechnet das Rückgeld.
 * Es wird überprüft, ob der eingegebene Geldbetrag ausreicht.
 * Falls zu wenig Geld eingeworfen wurde, wird der eingeworfene Betrag
 * als Rückgeld behandelt.
 * Das Rückgeld wird anschließend in Münzen aufgeteilt. Dabei wird immer die größt
 * mögliche Münze verwendet, beginnend mit 200, gefolgt von 100, 50, 20, 10, 5,
 * 2 und 1 Cent.
 * @param preis Preis des Produkts in Cent
 * @param geld Eingeworfener Geldbetrag in Cent
 * @return Array mit Anzahl der Münzen in folgender Reihenfolge:
 * [200, 100, 50, 20, 10, 5, 2, 1]
 */
public static int[] kaufeProdukt(int preis, int geld) {
	//Array zur Speicherung der Anzahl der Münzen
	int[] ergebnis = new int[8];
	//Betrag, der in Münzen aufgeteilt werden soll
	int zuWechseln;
	if(geld < preis) {
		System.out.println("Nicht genug Geld!");
		zuWechseln = geld;
	}
	else {
		//berechnet das Rückgeld
		zuWechseln = geld-preis;
	}
	//verfügbare Münzen
	int[] muenzWerte = {200, 100, 50, 20, 10, 5, 2, 1};
	//Schleife geht alle Münzen von Groß nach Klein durch und nimmt immer so viele wie möglich
	for(int i = 0; i < muenzWerte.length; i ++) {
		//berechnet, wie oft die Münze verwendet werden kann 
		ergebnis[i] = zuWechseln / muenzWerte[i];
		//reduziert den Restbetrag
		zuWechseln = zuWechseln % muenzWerte[i];
	}
	return ergebnis;
}
}
 
