package at.zachner.pages;

import javax.faces.bean.ManagedProperty;

public class Page{

	private boolean firstTimeOnPage;

	@ManagedProperty(value="#{sessionDataHolder}")
	private SessionDataHolder sdh;

	public void initPage() {
		if (getSdh().getAktuelleSeite() != null && getSdh().getAktuelleSeite().equals(this.getClass().getSimpleName())) {
			setFirstTimeOnPage(false);
		} else {
			setFirstTimeOnPage(true);
			getSdh().setAktuelleSeite(this.getClass().getSimpleName());
		}
	}

	public SessionDataHolder getSdh() {
		return sdh;
	}

	public void setSdh(SessionDataHolder sdh) {
		this.sdh = sdh;
	}

	public boolean isFirstTimeOnPage() {
		return firstTimeOnPage;
	}

	public void setFirstTimeOnPage(boolean firstTimeOnPage) {
		this.firstTimeOnPage = firstTimeOnPage;
	}

}
