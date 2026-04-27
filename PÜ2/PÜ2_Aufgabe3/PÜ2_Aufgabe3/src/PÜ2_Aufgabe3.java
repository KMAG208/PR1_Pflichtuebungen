import java.util.Scanner;

public class PÜ2_Aufgabe3 {
    public static void main(String[] args)  {
        
		//definition von Variablen
		Scanner in = new Scanner(System.in);
		int i;
		int j;
		
		//Erklärung des Programms
		System.out.println("Dieses Programm gibt alle Primzahlen kleiner gleich der eingegeben Zahl aus, \n Primzahlen können nur von Zahlen >= 2 berechnet werden");
		//Eingabeaufforderung
		System.out.println("Eingabe der maximalen Zahlenwerte dessen Primzahlen bestimmt werden sollen:");
		int n = in.nextInt();
		boolean[] Markierungen = new boolean[n + 1];

		//Berechnung der Primzahlen
		//Wenn das doppelte von i > als die Eingabe endet die Schleife
		for(i=2; i + i <= n; i = i + 1) {
			//j wird immer das doppelte von i gesetzt damit nur die Vielfachen durchlaufen werden
			for(j = i+i; j <= n; j = j + i) {
				//Speichern der Markierungen an den Stellen an welchen keine Primzahlen sind
			    Markierungen[j] = true;
			}
		}
		
		//Ausgabe der Primzahlen wenn keine Markierung an der Stelle vorhanden ist
		for(i=2; i<=n; i++) {
			if(Markierungen[i] == false){
				System.out.println(i);
			}
        }

        in.close();
    }
}
