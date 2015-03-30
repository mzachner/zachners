package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.zachner.data.lebenslauf.Lebenslauf;
import at.zachner.data.lebenslauf.PersoenlicheDaten;

@ManagedBean
@SessionScoped
public class LebenslaufView implements Serializable {

	private static final long serialVersionUID = 1L;

	public Lebenslauf getLebenslauf() {

		Lebenslauf l = new Lebenslauf();
		PersoenlicheDaten p = new PersoenlicheDaten();
		
		l.setPersoenlicheDaten(p);
		p.setVorname("Roman");
		p.setNachname("Zachner");
		p.setTitleVorher("Ing.");
		return l;
	}
}
