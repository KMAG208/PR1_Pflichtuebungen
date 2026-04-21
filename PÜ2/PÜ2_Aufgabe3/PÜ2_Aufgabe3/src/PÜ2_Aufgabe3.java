import java.util.Scanner;

public class PÜ2_Aufgabe3 {
    public static void main(String[] args)  {
        //Eingabe der Tastatur
		Scanner in = new Scanner(System.in);
		
		int i;
		int j;
		
		
		System.out.println("Eingabe der maximalen Zahlenwerte dessen Primzahlen bestimmt werden sollen:");
		int n = in.nextInt();
		
		boolean[] istPrimzahl = new boolean[n + 1];
		//Äußere Schleife
		for(i=2; i + i <= n; i = i + 1) {
			//Innere Schleife
			for(j = i+i; j <= n; j = j + i) {
			    istPrimzahl[j] = true;
			}
		}
		
		for(i=2; i<=n; i++) {
			if(istPrimzahl[i] == false)
				System.out.println(i);
        }

        in.close();
    }
}
