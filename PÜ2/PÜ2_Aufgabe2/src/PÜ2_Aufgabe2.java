import java.util.Scanner;

public class PÜ2_Aufgabe2 {

	public static void main(String[] args) {
		String input;

		Scanner in = new Scanner(System.in);
		String newString = "";
		System.out.print("Geben Sie eine Zeichenkette ein: ");
		input = in.nextLine();

		char [] charArray = new char[input.length()];
		for (int i = 0; i < input.length(); i++) {
			charArray[i] = input.charAt(i);
			if(charArray[i] > 64 && charArray[i] < 90 || charArray[i] > 96 && charArray[i] < 122) {
				charArray[i]++;
			}
			 else if (charArray[i] == 90 || charArray[i] == 122) {
				charArray[i] -= 25;
			}
			newString += charArray[i];
		}

		System.out.println("Die neue Zeichenkette lautet: " + newString);

		in.close();
	}
}
