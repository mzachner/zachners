package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class TicTacToe implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private GameConnection gC = GameConnection.getConnection(this);
	
	public String getSessionId() {
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
		return session.getId();
	}
	
	public String doAction(String x, String y) {
		gC.setToOtherSpieler(this);
		return ""; 
	}
	
	public String doAction(String action) {
		if (action.equals("abbruch")) {
			return "hello?faces-redirect=true";
		}
		if (action.equals("refresh")) {
			return "";
		}
		return ""; 
	}
	
	public boolean isMyTurn() {
		return gC.isMyTurn(this);
	}
}