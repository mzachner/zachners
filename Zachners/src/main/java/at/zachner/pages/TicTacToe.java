package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import at.zachner.service.tictactoe.GameConnection;

@ManagedBean
@SessionScoped
public class TicTacToe implements Serializable {

	private static final long serialVersionUID = 1L;

	private GameConnection gC;
	
	private boolean oldMyTurn;
	private boolean oldConnectionEstablished;
	private boolean refreshPage;

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
		if (action.equals("refresh")) {
			return "";
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

	public boolean isConnectionEstablished() {
		return getGameConnection().isConnectionEstablished();
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
	
	public boolean isWinnerButton(String x, String y) {
		return getGameConnection().isWinnerKoordinate(x, y);
	}
	
	public void pageRefresh() {
		refreshPage = false;
	}
	
	public boolean isRefreshPageRequired() {
		if (!refreshPage) {
			if (isSpielBeendet() == false && isMyTurn() == oldMyTurn && isConnectionEstablished() == oldConnectionEstablished) {
				return refreshPage;
			}
			oldMyTurn = isMyTurn();
			oldConnectionEstablished = isConnectionEstablished();
			refreshPage = true;
		}
		return refreshPage;
	}

}