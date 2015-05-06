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
	private int playerXLiveCounter;
	private int playerOLiveCounter;
	private boolean connectionEstablished;
	private TicTacToe aktuellerSpieler;
	private boolean spielBeendet;
	private boolean onePlayerNotAktiv;
	private TicTacToe playerGewinner;
	private Value[][] spielfeld = new Value[3][3];
	private boolean[][] gewinnerSpielfeld = new boolean[3][3];

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
	
	public synchronized void doCheckOtherPlayerAktiv(TicTacToe player) {
		if (connectionEstablished) {
			if (player == playerX) {
				playerOLiveCounter = 0;
				playerXLiveCounter++;
				if (playerXLiveCounter > 3) {
					onePlayerNotAktiv = true;
					spielBeendet = true;
				}
			}
			else {
				playerXLiveCounter = 0;
				playerOLiveCounter++;
				if (playerOLiveCounter > 3) {
					onePlayerNotAktiv = true;
					spielBeendet = true;
				}
			}
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
		return spielBeendet && !onePlayerNotAktiv && playerGewinner == null;
	}
	
	public synchronized boolean isOnePlayerNotAktiv() {
		return onePlayerNotAktiv;
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
				gewinnerSpielfeld[0][i] = true;
				gewinnerSpielfeld[1][i] = true;
				gewinnerSpielfeld[2][i] = true;
				return;
			}
			if (isZeileGleich(i, value)) {
				spielBeendet = true;
				playerGewinner = player;
				gewinnerSpielfeld[i][0] = true;
				gewinnerSpielfeld[i][1] = true;
				gewinnerSpielfeld[i][2] = true;
				return;
			}
		}
		if (isDiagonaleLoNachRuGleich(value)) {
			spielBeendet = true;
			playerGewinner = player;
			gewinnerSpielfeld[0][0] = true;
			gewinnerSpielfeld[1][1] = true;
			gewinnerSpielfeld[2][2] = true;
			return;
		}
		if (isDiagonaleLuNachRoGleich(value)) {
			spielBeendet = true;
			playerGewinner = player;
			gewinnerSpielfeld[2][0] = true;
			gewinnerSpielfeld[1][1] = true;
			gewinnerSpielfeld[0][2] = true;
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

	public boolean isWinnerKoordinate(int x, int y) {
		if (!isSpielBeendet()) {
			return false;
		}
		return gewinnerSpielfeld[x][y];
	}

	public synchronized boolean isXPlayer(TicTacToe player) {
		if (player == playerX) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public synchronized String getNameOfOtherPlayer(TicTacToe player) {
		if (player == playerX) {
			return playerO.getSdh().getAktuellerUser();
		}
		else {
			return playerX.getSdh().getAktuellerUser();
		}
	}

}