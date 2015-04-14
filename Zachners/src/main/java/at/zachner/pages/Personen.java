package at.zachner.pages;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.zachner.data.lebenslauf.Lebenslauf;
import at.zachner.service.PersonenDao;

@ManagedBean
@SessionScoped
public class Personen implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Lebenslauf aktuellerLebenslauf;

	public List<Lebenslauf> getLebenslaeufe() {
		
		return PersonenDao.getPersonen();
	}
	
	public String doAction(Lebenslauf lebenslauf) {
			setAktuellerLebenslauf(lebenslauf);
			return "lebenslaufView?faces-redirect=true";
	}

	public Lebenslauf getAktuellerLebenslauf() {
		return aktuellerLebenslauf;
	}

	public void setAktuellerLebenslauf(Lebenslauf aktuellerLebenslauf) {
		this.aktuellerLebenslauf = aktuellerLebenslauf;
	}

}
