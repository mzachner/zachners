package at.zachner.pages;

import java.io.Serializable;

public class GameConnection implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static GameConnection gC;
	
	private TicTacToe playerA;
	private TicTacToe playerB;
	private TicTacToe aktuellerSpieler;

	public static synchronized GameConnection getConnection(TicTacToe player) {
		if (gC == null) {
			gC = new GameConnection();
			gC.playerA = player;
			gC.aktuellerSpieler = player;
		}
		else {
			gC.playerB = player;
		}
		return gC;
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
		if (player == aktuellerSpieler && player == playerA) {
			this.aktuellerSpieler = playerB;
		}
		else if (player == aktuellerSpieler && player == playerB) {
			this.aktuellerSpieler = playerA;
		}
	}
	
}