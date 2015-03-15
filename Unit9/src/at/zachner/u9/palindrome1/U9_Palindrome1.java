package at.zachner.u9.palindrome1;

import java.util.Arrays;
import java.util.Scanner;

public class U9_Palindrome1 {

	public static void main(String[] args) {

		System.out.print("Enter a message: ");

		Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split("");
		//Du könntest auch bevor du den String mit split zerteilst den ganzen String in lowercase umwandeln
		//und danach erst mit split teilen.

		String[] smallLetters = { "a", "b", "c", "d", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z" };
		Arrays.sort(smallLetters); //Sortiert die Elemente nach der "natürlichen" Sortierung von String.
		//Damit binarySearch funktioniert muss das Array immer nach der natürlichen Sortierung sortiert sein.
		//Natürlich bei String heißt aufsteigend. Hast du eigentlich eh schon - daher in dem Fall unnötig.

		int in = 0;
		int sL = 0;
		int oL = 0;

		// How many letters are exist? oL Counter ... onlyLetters
		// Die Schliefe hast nur, damit du den Array mit der richtigen Länge erzeugen kannst.
		// Mit einer Liste wäre das einfacher, weil die dynamisch wachsen kann.
		do {
			if (Arrays.binarySearch(smallLetters, input[in].toLowerCase()) >= 0) {
				oL++;
			}
			//Noch eine Möglichkeit. Eine Liste bietet die Methode contains. Also vorher Array in Liste umwandeln.
//			if (Arrays.asList(smallLetters).contains(input[in])) {
//				oL++;
//			}
			in++;
		} while (in < input.length);

		String[] onlyLetters = new String[oL];
		in = 0;
		int c = 0;
		
		// Filling the onlyLetters Array with only small Letters!
		do {
			//Kann man noch weiter vereinfachen wie oben mit binarySearch.
			do {
				if (input[in].equalsIgnoreCase(smallLetters[sL])) {
					onlyLetters[c] = input[in].toLowerCase();
					c++;
					break;
				}
				else {
					sL++;
				}
			} while (sL < 25);
			in++;
			sL = 0;
		} while (in < input.length);

		boolean result = false;
		c = 0;

		// Is it an palindrome?
		if (oL < 2) {
			result = true;
		} else if (oL > 2 && oL % 2 == 1) { //abba ist auch ein Palindrome!!!!!!
			do {
				if (onlyLetters[c].equals(onlyLetters[oL - 1 - c])) {
					result = true;
					c++;
				} else {
					result = false;
					break;
				}
			} while (c < (oL - 1) / 2);
		}

		// Output
		//Das mit der Länge würd ich am Anfang gleich abfangen, weil sonst alles durchlaufen wird und
		//Prozessorzeit verwendet obwohl die Eingabe eh zu lang ist.
		if (input.length > 40) {
			System.out.println("The massage is longer than 40 characters!");
		} else {
			if (result == true) {
				System.out.println("Palindrome");
			} else {
				System.out.println("Not a palindrome");
			}
		}
	}
}
