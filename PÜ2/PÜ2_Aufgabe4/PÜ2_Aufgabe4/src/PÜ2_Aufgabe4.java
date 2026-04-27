import java.util.Scanner;

public class PÜ2_Aufgabe4 {
    public static void main(String[] args) {

        //Definition von Variablen
        Scanner in = new Scanner(System.in);
        int uebrig = 0;
        String ergebnis = "";

        //Arrays für die Werte und Symbole der römischen Zahlen
        int[] werte = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbole = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};        

        //Erklärung des Programms
        System.out.println("Dieses Programm konvertiert Dezimalzahlen in Römischezahlen \n Die Maximale Zahl ist 3999 und die Minimale Zahl ist 1.");
        System.out.println("Die Maximale Zahl ist 3999 und die Minimale Zahl ist 1.");

        //Eingabeaufforderung
        System.out.println("Geben Sie eine Zahl ein: ");
        
        //Auslesen der Eingabe und Überprüfung der Gültigkeit
        do {
            uebrig = in.nextInt();
            
            if(uebrig < 1 || uebrig > 3999){
                System.out.println("Die Zahl ist ungültig. Bitte geben Sie eine Zahl zwischen 1 und 3999 ein: ");
            }
        } while(uebrig < 1 || uebrig > 3999);

        //Berechnung der römischen Zahl. Berechnung startet mit dem höchsten Wert und arbeitet sich vor zum kleinsten Wert
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
