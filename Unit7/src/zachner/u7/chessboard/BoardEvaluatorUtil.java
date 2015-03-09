package zachner.u7.chessboard;

import java.util.HashMap;
import java.util.Map;

public class BoardEvaluatorUtil {

	//Durch das Schlüsselwort "static" wird diese Methode (Funktion) auch ohne Objekt verfügbar.
	//Das heißt statt new.BoardEvaluatorUtil.evaluate(...) kann man die Methode dann mit BoardEvaluatorUtil.evaluate(...) aufrufen.
	//Da du noch nicht objektorientiert programmierst sollten deine Methoden immer static sein.
	public static int evaluate(char board[][]) {

		int whiteValue = 0; //Das ist ein gut gewählter Name für die Variable.
		int blackValue = 0;

		for (int y = 0; y <= 7; y++) {
			for (int x = 0; x <= 7; x++) {
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

		return whiteValue - blackValue;
	}
	
	public static int evaluate2(char board[][]) {

		int whiteValue = 0;
		int blackValue = 0;
		
		Map<String,Integer> figureValues = new HashMap<>();
		//Eine Map ist eine Liste von Key-Value Paaren. Das heißt der Key ist in dem Fall ein String und der Value ist ein Integer.
		figureValues.put("q", 9); //Hier wird der Key "q" hinzugefügt mit dem Value 9.
		figureValues.put("r", 5);
		figureValues.put("b", 5);
		figureValues.put("n", 3);
		figureValues.put("p", 1);

		for (int y = 0; y <= 7; y++) {
			for (int x = 0; x <= 7; x++) {
				//String.valueOf(char) wandelt den char in einen String um
				//.containsKey(Key) wird geschaut ob der Key vorhanden ist in der Liste.
				if (figureValues.containsKey(String.valueOf(board[x][y]))) {
					//.get(Key) liefert den Value zurück der dem Key zugeordnet ist.
					blackValue += figureValues.get(String.valueOf(board[x][y]));
				}
				//mit .toLowerCase() wird der String zu einem String mit Kleinbuchstaben umgewandelt.
				else if (figureValues.containsKey(String.valueOf(board[x][y]).toLowerCase())) {
					whiteValue += figureValues.get(String.valueOf(board[x][y]).toLowerCase());
				}
			}
		}

		return whiteValue - blackValue;
	}

}
