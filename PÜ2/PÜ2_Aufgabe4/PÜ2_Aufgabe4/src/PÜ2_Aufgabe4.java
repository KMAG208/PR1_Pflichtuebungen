import java.util.Scanner;

public class PÜ2_Aufgabe4 {
    public static void main(String[] args) {

        //definition von Variablen
        Scanner in = new Scanner(System.in);
        int uebrig = 0;
        String ergebnis = "";

        //Arrays für die Werte und Symbole der römischen Zahlen
        int[] werte = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbole = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};        

        //Eingabeaufforderung
        System.out.println("Die Maximale Zahl ist 3999 und die Minimale Zahl ist 1.");
        System.out.print("Geben Sie eine Zahl ein: ");

        //Eingabe der Zahl und Überprüfung der Gültigkeit
        do {
            System.out.println("Geben Sie eine Zahl ein: ");
            uebrig = in.nextInt();
            if(uebrig < 1 || uebrig > 3999){
                System.out.println("Die Zahl ist ungültig. Bitte geben Sie eine Zahl zwischen 1 und 3999 ein: ");
            }

        } while(uebrig < 1 || uebrig > 3999);

        //Berechnung der römischen Zahl
        for (int i = 0; i < werte.length; i++) {
            //Solange die übrig gebliebene Zahl größer oder gleich dem aktuellen Wert ist
            while (uebrig >= werte[i]) {
                //Hinzufügen des entsprechenden Symbols zum Ergebnis und Abziehen des Wertes von der übrig gebliebenen Zahl
                ergebnis += symbole[i];
                uebrig -= werte[i];
            }
        }

        //Ausgabe des Ergebnisses
        System.out.println("Das Ergebnis ist: " + ergebnis);
        in.close();

    }
}
