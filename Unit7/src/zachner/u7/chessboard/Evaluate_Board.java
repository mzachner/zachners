package zachner.u7.chessboard;

public class Evaluate_Board {

	public int board(char board[][]) {

		int whiteValue = 0;
		int blackValue = 0;

		int x;
		int y;

		for (y = 0; y <= 7; y++) {
			for (x = 0; x <= 7; x++) {
				if (board[x][y] == 'q') {
					blackValue = blackValue + 9;
				} else if (board[x][y] == 'Q') {
					whiteValue = whiteValue + 9;
				} else if (board[x][y] == 'r') {
					blackValue = blackValue + 5;
				} else if (board[x][y] == 'R') {
					whiteValue = whiteValue + 5;
				} else if (board[x][y] == 'b') {
					blackValue = blackValue + 5;
				} else if (board[x][y] == 'B') {
					whiteValue = whiteValue + 5;
				} else if (board[x][y] == 'n') {
					blackValue = blackValue + 3;
				} else if (board[x][y] == 'N') {
					whiteValue = whiteValue + 3;
				} else if (board[x][y] == 'p') {
					blackValue = blackValue + 1;
				} else if (board[x][y] == 'P') {
					whiteValue = whiteValue + 1;
				}
			}
		}

		int score = whiteValue - blackValue;

		return score;
	}

}
