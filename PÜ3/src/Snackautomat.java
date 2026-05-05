import java.util.Scanner;

public class Snackautomat {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String[][] produkte = {
            {"Wasser", "Cola", "Saft", "Kaffee"},
            {"Chips", "Schokolade", "Nuesse", "Muesliriegel"},
            {"Apfel", "Banane", "Trauben", "Birne", "Mandarine"}
        };

        int[][] preise = {
            {100, 150, 120, 185},
            {199, 180, 225, 165},
            {85, 92, 111, 95, 89}
        };

        String[] kategorien = {"G - Getränke", "S - Snacks", "O - Obst"};

        String eProdukt;
        String ePreis;

        zeigeProdukte(produkte, kategorien);


    }

    /**
     * Gibt alle Produkte nach Kategorien geordnet aus.
     * Jede Kategorie wird mit ihren jeweiligen Produkten
     * in einer neuen Zeile dargestellt.
     * Jedes Produkt wird mit seiner passenden Nummer angezeigt.
     * @param produkte Array von Arrays mit Produktnamen
     */
    public static void zeigeProdukte(String[][] produkte, String[] kategorien){
        for(int i = 0; i < kategorien.length; i++){
            System.out.println(kategorien[i]+ " [ ");
            for(int j = 0; j < produkte[i].length; j++){
                System.out.print(j+ " : "+ produkte[i][j]);
            }
            System.out.print("]");
        }
    }
}
