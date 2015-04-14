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

	private GameConnection getGameConnection() {
		if (gC == null) {
			gC = GameConnection.getConnection(this);
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
	
	public boolean isDisabled(String x, String y) {
		return !isMyTurn()
				|| getGameConnection().getSpielfeld()[Integer.valueOf(x)][Integer
						.valueOf(y)] != null;
	}
	
	public boolean isWinnerButton(String x, String y) {
		return getGameConnection().isWinnerKoordinate(x, y);
	}

}