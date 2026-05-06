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

            System.out.print(getKategorien()[i]+ " [ ");
            //Innere Schleife geht durch jedes Produkt in der aktuellen Kategorie
            for(int j = 0; j < produkte[i].length; j++){
            	//j+1 damit der Nutzer Nummern ab 1 sieht 
                System.out.print(j+1+ ": "+ produkte[i][j]+ " ");
            }
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
        default: return 0;
    }
}
}
