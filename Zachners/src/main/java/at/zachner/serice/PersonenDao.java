package at.zachner.serice;

import java.util.ArrayList;
import java.util.List;

import at.zachner.data.lebenslauf.Lebenslauf;
import at.zachner.data.lebenslauf.PersoenlicheDaten;

public class PersonenDao {
	
	public static List<Lebenslauf> getPersonen() {
		Lebenslauf l1 = new Lebenslauf();
		PersoenlicheDaten p1 = new PersoenlicheDaten();
		
		l1.setPersoenlicheDaten(p1);
		p1.setVorname("Roman");
		p1.setNachname("Zachner");
		p1.setTitleVorher("Ing.");
		
		Lebenslauf l2 = new Lebenslauf();
		PersoenlicheDaten p2 = new PersoenlicheDaten();
		
		l2.setPersoenlicheDaten(p2);
		p2.setVorname("Martin");
		p2.setNachname("Zachner");
		p2.setTitleVorher("Ing.");
		
		List<Lebenslauf> ret = new ArrayList<>();
		ret.add(l1);
		ret.add(l2);
		
		return ret;
		
	}
	
	public static Lebenslauf getPerson(String Vorname) {
		Lebenslauf l1 = new Lebenslauf();
		PersoenlicheDaten p1 = new PersoenlicheDaten();
		
		l1.setPersoenlicheDaten(p1);
		p1.setVorname("Roman");
		p1.setNachname("Zachner");
		p1.setTitleVorher("Ing.");
		
		return l1;
	}

}
