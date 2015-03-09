package zachner.u8.autowalker;

public class DirectionUtil {

	public static boolean prove(int d, int x, int y, String field[][]) {

		boolean possible = false;

		if (d == 0 && y > 0 && field[x][y - 1].equals(".")) {
			possible = true;
		} else if (d == 1 && x < 9 && field[x + 1][y].equals(".")) {
			possible = true;
		} else if (d == 2 && y < 9 && field[x][y + 1].equals(".")) {
			possible = true;
		} else if (d == 3 && x > 0 && field[x - 1][y].equals(".")) {
			possible = true;
		}

		return possible;
	}

}
