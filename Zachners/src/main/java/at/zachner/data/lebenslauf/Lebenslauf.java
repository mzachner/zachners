package at.zachner.data.lebenslauf;

import java.util.List;

public class Lebenslauf {

	private PersoenlicheDaten persoenlicheDaten;

	private List<BeruflicheWerdegangseintrag> beruflicheWerdegang;

	private List<SchulischeWerdegangseintrag> schulischeWerdegang;

	private FaehigkeitenKenntnisse faehigkeitenKenntnisse;

	public PersoenlicheDaten getPersoenlicheDaten() {
		return persoenlicheDaten;
	}

	public void setPersoenlicheDaten(PersoenlicheDaten persoenlicheDaten) {
		this.persoenlicheDaten = persoenlicheDaten;
	}

	public List<BeruflicheWerdegangseintrag> getBeruflicheWerdegang() {
		return beruflicheWerdegang;
	}

	public void setBeruflicheWerdegang(
			List<BeruflicheWerdegangseintrag> beruflicheWerdegang) {
		this.beruflicheWerdegang = beruflicheWerdegang;
	}

	public List<SchulischeWerdegangseintrag> getSchulischeWerdegang() {
		return schulischeWerdegang;
	}

	public void setSchulischeWerdegang(
			List<SchulischeWerdegangseintrag> schulischeWerdegang) {
		this.schulischeWerdegang = schulischeWerdegang;
	}

	public FaehigkeitenKenntnisse getFaehigkeitenKenntnisse() {
		return faehigkeitenKenntnisse;
	}

	public void setFaehigkeitenKenntnisse(
			FaehigkeitenKenntnisse faehigkeitenKenntnisse) {
		this.faehigkeitenKenntnisse = faehigkeitenKenntnisse;
	}

}
