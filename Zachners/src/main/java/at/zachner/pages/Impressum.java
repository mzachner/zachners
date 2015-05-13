package at.zachner.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Impressum extends Page implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	public String doAction(String action) {
		if (action.equals("zurueck")) {
			return "hauptmenu?faces-redirect=true";
		}
		return "";
	}
}