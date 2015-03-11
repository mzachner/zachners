package at.zachner.u9.palindrome1;

import java.util.Scanner;

public class U9_Palindrome1 {

	public static void main(String[] args) {

		System.out.print("Enter a message: ");

		Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split("");

		String[] smallLetters = { "a", "b", "c", "d", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z" };

		int in = 0;
		int sL = 0;
		int oL = 0;

		// How many letters are exist? oL Counter ... onlyLetters
		do {
			do {
				if (input[in].equals(smallLetters[sL])
						|| input[in].equals(smallLetters[sL].toUpperCase())) {
					oL++;
					break;
				} else {
					sL++;
				}
			} while (sL < 25);
			in++;
			sL = 0;
		} while (in < input.length);

		String[] onlyLetters = new String[oL];
		in = 0;
		int c = 0;

		// Filling the onlyLetters Array with only small Letters!
		do {
			do {
				if (input[in].equals(smallLetters[sL])) {
					onlyLetters[c] = input[in];
					c++;
					break;
				} else if (input[in].equals(smallLetters[sL].toUpperCase())) {
					onlyLetters[c] = input[in].toLowerCase();
					c++;
					break;
				} else {
					sL++;
				}
			} while (sL < 25);
			in++;
			sL = 0;
		} while (in < input.length);

		boolean request = false;
		c = 0;

		// Is it an palindrome?
		if (oL < 2) {
			request = true;
		} else if (oL > 2 && oL % 2 == 1) {
			do {
				if (onlyLetters[c].equals(onlyLetters[oL - 1 - c])) {
					request = true;
					c++;
				} else {
					request = false;
				}
			} while (c < (oL - 1) / 2);
		}

		// Output
		if (input.length > 40) {
			System.out.println("The massage is longer than 40 characters!");
		} else {
			if (request == true) {
				System.out.println("Palindrome");
			} else {
				System.out.println("Not a palindrome");
			}
		}
	}
}
