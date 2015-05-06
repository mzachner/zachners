package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import at.zachner.service.tictactoe.GameConnection;

@ManagedBean
@SessionScoped
public class TicTacToe extends Page implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String GREEN = "rgb(0,200,0)";
	private static final String RED = "rgb(255,0,0)";

	private GameConnection gC;
	
	private boolean oldMyTurn;
	private boolean oldConnectionEstablished;
	private boolean refreshPage;
	
	public void pageRefresh() {
		refreshPage = false;
		if (isFirstTimeOnPage()) {
			gC = null;
		}
	}
	
	public boolean isRefreshPageRequired() {
		if (!refreshPage) {
			getGameConnection().doCheckOtherPlayerAktiv(this);
			if (isSpielBeendet() == false && isMyTurn() == oldMyTurn && isConnectionEstablished() == oldConnectionEstablished) {
				return refreshPage;
			}
			oldMyTurn = isMyTurn();
			oldConnectionEstablished = isConnectionEstablished();
			refreshPage = true;
		}
		return refreshPage;
	}


	private GameConnection getGameConnection() {
		if (gC == null) {
			gC = GameConnection.getConnection(this);
			oldMyTurn = isMyTurn();
			oldConnectionEstablished = isConnectionEstablished();
			refreshPage = true;
		}
		return gC;
	}

	public String getSessionId() {
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext()
				.getSession(false);
		return session.getId();
	}

	public String doAction(String x, String y) {
		getGameConnection().setFeld(this, Integer.valueOf(x),
				Integer.valueOf(y));
		// gC.setToOtherSpieler(this);
		oldMyTurn = isMyTurn();
		return "";
	}

	public String doAction(String action) {
		if (action.equals("abbruch")) {
			gC = null;
			return "hauptmenu?faces-redirect=true";
		}
		else if (action.equals("neuesSpiel")) {
			gC = null;
			return "ticTacToe?faces-redirect=true";
		}
		return "";
	}

	public boolean isMyTurn() {
		return getGameConnection().isMyTurn(this);
	}

	public boolean isSpielBeendet() {
		return getGameConnection().isSpielBeendet();
	}

	public boolean isWinner() {
		return getGameConnection().isWinner(this);
	}

	public boolean isLooser() {
		return getGameConnection().isLooser(this);
	}

	public boolean isUnentschieden() {
		return getGameConnection().isUnentschieden();
	}
	
	public boolean isOnePlayerNotAktiv() {
		return getGameConnection().isOnePlayerNotAktiv();
	}

	public boolean isConnectionEstablished() {
		return getGameConnection().isConnectionEstablished();
	}
	
	public String getXColor() {
		if (getGameConnection().isXPlayer(this)) {
			return GREEN;
		}
		else {
			return RED;
		}
	}
	
	public String getOColor() {
		if (getGameConnection().isXPlayer(this)) {
			return RED;
		}
		else {
			return GREEN;
		}
	}

	public String getFeldValue(String x, String y) {
		GameConnection.Value v = getGameConnection().getSpielfeld()[Integer
				.valueOf(x)][Integer.valueOf(y)];
		return v == null ? "" : v.name();
	}
	
	public boolean isNotDisabled(String x, String y) {
		return isConnectionEstablished() && isMyTurn() && !isSpielBeendet()
				&& getGameConnection().getSpielfeld()[Integer.valueOf(x)][Integer
						.valueOf(y)] == null;
	}
	
	public String isWinnerField(String x, String y) {
		return getGameConnection().isWinnerKoordinate(Integer.valueOf(x), Integer.valueOf(y)) ? "winnerField" : "";
	}
	
	public String getNameOfOtherPlayer() {
		String ret = getGameConnection().getNameOfOtherPlayer(this);
		if (ret == null || ret.isEmpty()) {
			return "unknown";
		}
		else {
			return ret;
		}
	}

}