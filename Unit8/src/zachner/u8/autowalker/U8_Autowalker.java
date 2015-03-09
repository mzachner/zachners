package zachner.u8.autowalker;

import java.util.Scanner;

public class U8_Autowalker {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.print("Max consecutive steps: ");
		int sMax = scanner.nextInt();
		System.out.print("Direction initializer: ");
		int d = scanner.nextInt();
		System.out.print("Turn direction: ");
		int t = scanner.nextInt();
		System.out.print("Start Pos X: ");
		int x = scanner.nextInt();
		System.out.print("Start Pos Y: ");
		int y = scanner.nextInt();
		System.out.println("");

		String[][] field = new String[10][10];

		// filling with "."
		for (int yfield = 0; yfield < 10; yfield++) {
			for (int xfield = 0; xfield < 10; xfield++) {
				field[xfield][yfield] = ".";
			}
		}

		String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		int le = 0;// lettercounter
		int s = 0;// steps
		int finish = 0;// for finishing the loop

		do {

			field[x][y] = letter[le];

			if (DirectionUtil.prove(d, x, y, field) == true && s < sMax) {
				if (le == 25) {
					le = 0;
				} else {
					le++;
				}

				if (d == 0) {
					y--;
				} else if (d == 1) {
					x++;
				} else if (d == 2) {
					y++;
				} else if (d == 3) {
					x--;
				}
				s++;
				finish = 0;
			} else {
				if (d < 4) {
					d = d + t;
				} else {
					d = 0;
				}
				s = 0;
				finish++;
			}
		} while (finish <= 4);

		// Printout of the Array
		System.out.println("Final board:");
		for (y = 0; y < 10; y++) {
			for (x = 0; x < 10; x++) {
				System.out.print(field[x][y]);
			}
			System.out.println("");
		}
	}
}
