package zachner.u7.chessboard.test;

import zachner.u7.chessboard.Evaluate_Board;

public class U7_Chessboard {

	public static void main(String[] args) {

		char[][] board = new char[8][8];

		for (int y = 0; y <= 7; y++) {
			for (int x = 0; x <= 7; x++) {
				board[x][y] = '.';
			}
		}
		board[0][0] = 'r';
		board[1][0] = 'n';
		board[2][0] = 'b';
		board[3][0] = 'q';
		board[4][0] = 'k';
		board[5][0] = 'b';
		board[6][0] = 'n';
		board[7][0] = 'r';
		board[0][1] = 'p';
		board[1][1] = 'p';
		board[2][1] = 'p';
		board[3][1] = 'p';
		board[4][1] = 'p';
		board[5][1] = 'p';
		board[6][1] = 'p';
		board[7][1] = 'p';
		board[0][7] = 'R';
		board[1][7] = 'N';
		board[2][7] = 'B';
		board[3][7] = 'Q';
		board[4][7] = 'K';
		board[5][7] = 'B';
		board[6][7] = 'N';
		board[7][7] = 'R';
		board[0][6] = 'P';
		board[1][6] = 'P';
		board[2][6] = 'P';
		board[3][6] = 'P';
		board[4][6] = 'P';
		board[5][6] = 'P';
		board[6][6] = 'P';
		board[7][6] = 'P';

		int x = 0;
		int y = 0;

		for (y = 0; y <= 7; y++) {
			for (x = 0; x <= 7; x++) {
				System.out.print(board[x][y]);
			}
			System.out.println("");
		}

		Evaluate_Board evaluate = new Evaluate_Board();

		System.out.print("Score: " + evaluate.evaluate(board));

	}

}
