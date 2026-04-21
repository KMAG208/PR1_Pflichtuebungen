import java.util.Scanner;

public class PÜ2_Aufgabe3 {
    public static void main(String[] args)  {
        
		//definition von Variablen
		Scanner in = new Scanner(System.in);
		int i;
		int j;
		
		//Eingabeaufforderung
		System.out.println("Eingabe der maximalen Zahlenwerte dessen Primzahlen bestimmt werden sollen:");
		int n = in.nextInt();
		boolean[] istPrimzahl = new boolean[n + 1];

		//Berechnung der Primzahlen
		for(i=2; i + i <= n; i = i + 1) {
			for(j = i+i; j <= n; j = j + i) {
				//speichern der Primzahlen in einem Array
			    istPrimzahl[j] = true;
			}
		}
		
		//Ausgabe der Primzahlen wenn die Stelle im Array den Wert false hat
		for(i=2; i<=n; i++) {
			if(istPrimzahl[i] == false){
				System.out.println(i);
			}
        }

        in.close();
    }
}
