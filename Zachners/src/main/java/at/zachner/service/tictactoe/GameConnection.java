package at.zachner.service.tictactoe;

import java.io.Serializable;

import at.zachner.pages.TicTacToe;

public class GameConnection implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum Value {
		X, O
	};

	private static GameConnection gC;

	private TicTacToe playerX;
	private TicTacToe playerO;
	private boolean connectionEstablished;
	private TicTacToe aktuellerSpieler;
	private boolean spielBeendet;
	private TicTacToe playerGewinner;
	private int[][] gewinnerKoordinaten = new int[3][2];
	private Value[][] spielfeld = new Value[3][3];

	public static synchronized GameConnection getConnection(TicTacToe player) {
		if (gC == null) {
			gC = new GameConnection();
			gC.playerX = player;
			gC.aktuellerSpieler = player;
			return gC;
		}
		if (player != gC.playerX) {
			gC.playerO = player;
			gC.connectionEstablished = true;
			GameConnection gCKomplett = gC;
			gC = null;
			return gCKomplett;
		} else {
			return gC;
		}
	}

	public synchronized boolean isConnectionEstablished() {
		return connectionEstablished;
	}

	public synchronized void setFeld(TicTacToe player, int x, int y) {
		if (isConnectionEstablished()) {
			if (isMyTurn(player)) {
				if (!spielBeendet) {
					if (player == playerX) {
						spielfeld[x][y] = Value.X;
					} else {
						spielfeld[x][y] = Value.O;
					}
					spielAuswerten(player);
					setToOtherSpieler(player);
				}
			}
		}
	}

	public synchronized boolean isSpielBeendet() {
		return spielBeendet;
	}

	public synchronized boolean isWinner(TicTacToe player) {
		return player == playerGewinner;
	}

	public synchronized boolean isLooser(TicTacToe player) {
		return playerGewinner != null && playerGewinner != player;
	}

	public synchronized boolean isUnentschieden() {
		return spielBeendet && playerGewinner == null;
	}

	private synchronized void spielAuswerten(TicTacToe player) {
		Value value;
		if (player == playerX) {
			value = Value.X;
		} else {
			value = Value.O;
		}

		// Ist der player der Gewinner?
		for (int i = 0; i <= 2; i++) {
			if (isSpalteGleich(i, value)) {
				spielBeendet = true;
				playerGewinner = player;
				gewinnerKoordinaten[0][0] = 0;
				gewinnerKoordinaten[0][1] = i;
				gewinnerKoordinaten[1][0] = 1;
				gewinnerKoordinaten[1][1] = i;
				gewinnerKoordinaten[2][0] = 2;
				gewinnerKoordinaten[2][1] = i;
				return;
			}
			if (isZeileGleich(i, value)) {
				spielBeendet = true;
				playerGewinner = player;
				gewinnerKoordinaten[0][0] = i;
				gewinnerKoordinaten[0][1] = 0;
				gewinnerKoordinaten[1][0] = i;
				gewinnerKoordinaten[1][1] = 1;
				gewinnerKoordinaten[2][0] = i;
				gewinnerKoordinaten[2][1] = 2;
				return;
			}
		}
		if (isDiagonaleLoNachRuGleich(value)) {
			spielBeendet = true;
			playerGewinner = player;
			gewinnerKoordinaten[0][0] = 0;
			gewinnerKoordinaten[0][1] = 0;
			gewinnerKoordinaten[1][0] = 1;
			gewinnerKoordinaten[1][1] = 1;
			gewinnerKoordinaten[2][0] = 2;
			gewinnerKoordinaten[2][1] = 2;
			return;
		}
		if (isDiagonaleLuNachRoGleich(value)) {
			spielBeendet = true;
			playerGewinner = player;
			gewinnerKoordinaten[0][0] = 2;
			gewinnerKoordinaten[0][1] = 0;
			gewinnerKoordinaten[1][0] = 1;
			gewinnerKoordinaten[1][1] = 1;
			gewinnerKoordinaten[2][0] = 0;
			gewinnerKoordinaten[2][1] = 2;
			return;
		}

		// Unentschieden?
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (spielfeld[j][i] == null) {
					return;
				}
			}
		}
		spielBeendet = true;

	}

	private synchronized boolean isSpalteGleich(int spalte, Value value) {
		return spielfeld[0][spalte] == value && spielfeld[1][spalte] == value
				&& spielfeld[2][spalte] == value;
	}

	private synchronized boolean isZeileGleich(int zeile, Value value) {
		return spielfeld[zeile][0] == value && spielfeld[zeile][1] == value
				&& spielfeld[zeile][2] == value;
	}

	private synchronized boolean isDiagonaleLoNachRuGleich(Value value) {
		return spielfeld[0][0] == value && spielfeld[1][1] == value
				&& spielfeld[2][2] == value;
	}

	private synchronized boolean isDiagonaleLuNachRoGleich(Value value) {
		return spielfeld[2][0] == value && spielfeld[1][1] == value
				&& spielfeld[0][2] == value;
	}

	/**
	 * Spielfeld ist keine Kopie. Bitte nicht ändern.
	 */
	public synchronized Value[][] getSpielfeld() {
		return spielfeld;

	}

	public synchronized boolean isMyTurn(TicTacToe player) {
		if (player == aktuellerSpieler) {
			return true;
		} else {
			return false;
		}
	}

	private synchronized void setToOtherSpieler(TicTacToe player) {
		if (player == aktuellerSpieler && player == playerX) {
			this.aktuellerSpieler = playerO;
		} else if (player == aktuellerSpieler && player == playerO) {
			this.aktuellerSpieler = playerX;
		}
	}

	public boolean isWinnerKoordinate(String x, String y) {
		// TODO Auto-generated method stub
		return false;
	}

}