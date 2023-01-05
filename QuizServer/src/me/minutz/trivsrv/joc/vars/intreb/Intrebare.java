package me.minutz.trivsrv.joc.vars.intreb;

public class Intrebare {
	private String intrebare,raspuns;
	private ITip tip;
	private Categ categ;

	public Intrebare(String intrebare, String raspuns, ITip tip, Categ categorie) {
		super();
		this.intrebare = intrebare;
		this.raspuns = raspuns;
		this.tip = tip;
		categ=categorie;
	}

	public String getIntrebare() {
		return intrebare;
	}

	public String getRaspuns() {
		return raspuns;
	}

	public ITip getTip() {
		return tip;
	}
	
	public Categ getCateg(){
		return categ;
	}

	public void setIntrebare(String intrebare) {
		this.intrebare = intrebare;
	}

	public void setRaspuns(String raspuns) {
		this.raspuns = raspuns;
	}

	public void setTip(ITip tip) {
		this.tip = tip;
	}

	public void setCateg(Categ categ) {
		this.categ = categ;
	}
	
}
