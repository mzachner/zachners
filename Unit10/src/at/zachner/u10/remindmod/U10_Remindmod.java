package at.zachner.u10.remindmod;

import java.util.Scanner;

public class U10_Remindmod {

	public static boolean proveStructure(String input) {

		String[] inputSplit = input.split("");
		boolean prove;

		int i = 0;
		String numbers = "0123456789";

		if (!input.contains("/") || !input.contains(" ")
				|| !input.contains(":")) {
			prove = false;
		} else {
			do {
				prove = false;
				if (i < inputSplit.length) {
					if (numbers.contains(inputSplit[i])) {
						prove = true;
					} else if (inputSplit[i].equals("/")) {
						if (i == 1 || i == 2) {
							prove = true;
						}
					} else if (inputSplit[i].equals(" ")) {
						if (inputSplit[i - 2].equals("/")
								|| inputSplit[i - 3].equals("/")) {
							prove = true;
						} else if (inputSplit[i - 2].equals(":")
								|| inputSplit[i - 3].equals(":")) {
							prove = true;
							break;
						}
					} else if (inputSplit[i].equals(":")) {
						if (inputSplit[i - 2].equals(" ")
								|| inputSplit[i - 3].equals(" ")) {
							prove = true;
						}
					}
				}
				i++;

			} while (prove == true);
		}

		return prove;
	}

	public static boolean proveContent(int month, int day, int hours,
			int minutes) {

		boolean prove = false;

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			if (day >= 1 && day <= 31) {
				if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
					prove = true;
				}
			}
		} else if (month == 2) {
			if (day >= 1 && day <= 29) {
				if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
					prove = true;
				}
			}

		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day >= 1 && day <= 30) {
				if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
					prove = true;
				}
			}
		}

		return prove;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		boolean finish = false;

		String[][][][] inputStorage = new String[13][32][24][60];

		do {

			System.out
					.println("Enter reminder [month/day hours:minutes message]:");

			String input = scanner.nextLine();

			if (proveStructure(input) == true) {
				String[] inputSplit = input.split("/|\\ |\\:|\\ ");

				int month = Integer.parseInt(inputSplit[0]);
				int day = Integer.parseInt(inputSplit[1]);
				int hours = Integer.parseInt(inputSplit[2]);
				int minutes = Integer.parseInt(inputSplit[3]);
				String massage = "";
				int i = 4;
				do {
					massage = massage + inputSplit[i] + " ";
					i++;
				} while (i < inputSplit.length);

				if (proveContent(month, day, hours, minutes) == true) {

					inputStorage[month][day][hours][minutes] = massage;

				}

				else if (month == 0 | day == 0) {
					finish = true;
				} else {
					System.out
							.println("Month, day, hours or mintues are not possible!");
				}

			} else if (input.length() == 1 && input.equals("0")) {
				finish = true;
			} else {
				System.out.println("The Structure of the input isn't correct!");
			}

		} while (finish == false);

		int month = 0;
		int day = 0;
		int hours = 0;
		int minutes = 0;

		System.out.println("One-Year Reminder");

		do {

			do {

				do {
					do {
						if (inputStorage[month][day][hours][minutes] != null) {
							System.out.printf("%2d/%2d %02d:%02d %s\n", month,
									day, hours, minutes,
									inputStorage[month][day][hours][minutes]);
						}
						minutes++;
					} while (minutes < 60);
					minutes = 0;
					hours++;
				} while (hours < 24);
				hours = 0;
				day++;
			} while (day <= 31);
			day = 0;
			month++;
		} while (month <= 12);

	}

}
