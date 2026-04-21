import java.util.Scanner;

public class Aufgabe1 {
	public static void main(String[] args) {
		
		//definition von Variablen
		Scanner in = new Scanner(System.in);
		int eingabe;
		boolean isPrim = true;
		
		System.out.println("Dieses Programm gibt alle Primzahlen kleiner gleich der eingegeben Zahl aus, \n Wird beendet durch eingabe einer Zahl kleiner als 1");
		
		//Berechnungsschleife
		do {

			//Eingabeaufforderung
			System.out.println("Bitte geben sie ihre Zahl ein:");
			eingabe = in.nextInt();
			
			//Berechnung der Primzahlen
			for(int i = 2; i <= eingabe; i++) {
				for(int j = 2; j< i && isPrim; j++) {
					if(i%j == 0) {
						isPrim = false;
					}
				}

				//Ausgabe der Primzahl
				if(isPrim) {
					System.out.println(i);
				}

				//Zurücksetzen der Variable für die nächste Zahl
				isPrim = true;
			}
			
			//Abschlussmeldung
			System.out.println("Dies waren alle Primzahlen <="+ eingabe);
			
		//Schleifenbedingung	
		}while(eingabe > 1);
		
		//Abschlussmeldung
		System.out.println("... und tschüss");
		in.close();

	}
}
