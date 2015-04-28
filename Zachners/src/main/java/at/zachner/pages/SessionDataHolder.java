package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionDataHolder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String aktuelleSeite;

	public String getAktuelleSeite() {
		return aktuelleSeite;
	}

	public void setAktuelleSeite(String aktuelleSeite) {
		this.aktuelleSeite = aktuelleSeite;
	}
}
