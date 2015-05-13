package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/* 
TODO: Woher kommen die Fehlermeldungen am Anfang ganz unten? DONE (geht nicht weg - egal ignorieren)
TODO: Spiel mit Passwort
TODO: Zurückbutton geht nicht richtig. DONE (besser gehts nicht. Wenn man im Hauptmenu ist, dann wird ein neues Spiel gestartet.)
TODO: Was ist wenn ein Spieler das Spiel verlässt? DONE
TODO: Im Chrome ist das Layout instabil.
TODO: Wie ist das jetzt mit den Filtern und Sicherheit? DONE (Sicherheit gibts nicht weil kein Login)
TODO: Warum kann ich nicht einfach auf einer alten Seite, wo die Session weg ist weil Serverneustart, irgendeinen Button drücken? Kann man da nicht einfach umleiten und neue Session vergeben. DONE
TODO: Timeout wenn Server nicht reagiert, brauch ich nicht mehr AJAX Calls schicken."
TODO: Auf Handy testen und deployn.
*/

@ManagedBean
@SessionScoped
public class Hauptmenu extends Page implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	public String doAction(String action) {
		if (action.equals("TicTacToe")) {
			initPage(); //Wenn der Zurückbutton verwendet wurde, dann wird hiermit das Hauptmenu als aktuelle Seite gesetzt.
			return "ticTacToe?faces-redirect=true";
		}
		if (action.equals("impressum")) {
			return "impressum?faces-redirect=true";
		}
		return "";
	}
	
	public String getAktuellerUser() {
		return getSdh().getAktuellerUser();
	}

	public void setAktuellerUser(String aktuellerUser) {
		getSdh().setAktuellerUser(aktuellerUser);
	}

}