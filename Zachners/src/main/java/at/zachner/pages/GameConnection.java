package at.zachner.pages;

import java.io.Serializable;

public class GameConnection implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static GameConnection gC;
	
	private TicTacToe playerX;
	private TicTacToe playerO;
	private TicTacToe aktuellerSpieler;
	private String[][] spielfeld = {{"","",""},{"","",""},{"","",""}};

	public static synchronized GameConnection getConnection(TicTacToe player) {
		if (gC == null) {
			gC = new GameConnection();
			gC.playerX = player;
			gC.aktuellerSpieler = player;
			return gC;
		}
		gC.playerO = player;
		GameConnection gCKomplett = gC;
		gC = null;
		return gCKomplett;
	}
	
	public synchronized void setFeld(TicTacToe player, int x, int y) {
		if (isMyTurn(player)) {
			if (player == playerX) {
				spielfeld[x][y] = "X";
			}
			else {
				spielfeld[x][y] = "O";
			}
		}
	}
	
	public synchronized String[][] getSpielfeld() {
		return spielfeld;
		
	}

	public synchronized boolean isMyTurn(TicTacToe player) {
		if (player == aktuellerSpieler) {
			return true;
		}
		else {
			return false;
		}
	}

	public synchronized void setToOtherSpieler(TicTacToe player) {
		if (player == aktuellerSpieler && player == playerX) {
			this.aktuellerSpieler = playerO;
		}
		else if (player == aktuellerSpieler && player == playerO) {
			this.aktuellerSpieler = playerX;
		}
	}
	
}