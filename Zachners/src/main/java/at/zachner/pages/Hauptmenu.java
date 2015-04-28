package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Hauptmenu extends Page implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	public String doAction(String action) {
		if (action.equals("TicTacToe")) {
			initPage(); //Wenn der Zurückbutton verwendet wurde, dann wird hiermit das Hauptmenu als aktuelle Seite gesetzt.
			return "ticTacToe?faces-redirect=true";
		}
		return "";
	}

}