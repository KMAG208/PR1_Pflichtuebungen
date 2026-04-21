import java.util.Scanner;

public class PÜ2_Aufgabe2 {
	public static void main(String[] args) {
		
		//definition von Variablen
		String input;
		Scanner in = new Scanner(System.in);
		String newString = "";

		//Eingabeaufforderung
		System.out.print("Geben Sie eine Zeichenkette ein: ");
		input = in.nextLine();
		char [] charArray = new char[input.length()];

		//Umrechnung der Zeichenkette
		for (int i = 0; i < input.length(); i++) {

			//Auslesen des Zeichen an der aktuellen Stelle
			charArray[i] = input.charAt(i);

			//Abändern des Zeichens bei gegebenen Bedingungen
			if(charArray[i] > 64 && charArray[i] < 90 || charArray[i] > 96 && charArray[i] < 122) {
				charArray[i]++;
			}
			 else if (charArray[i] == 90 || charArray[i] == 122) {
				charArray[i] -= 25;
			}
			
			//Hinzufügen des neuen Zeichens zur neuen Zeichenkette
			newString += charArray[i];
		}

		//Ausgabe der neuen Zeichenkette
		System.out.println("Die neue Zeichenkette lautet: " + newString);

		in.close();
	}
}
