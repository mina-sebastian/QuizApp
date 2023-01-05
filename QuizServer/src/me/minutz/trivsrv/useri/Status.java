package me.minutz.trivsrv.useri;

public class Status {

	private Clasa scut,resurse,oameni;

	public Status(Clasa scut, Clasa resurse, Clasa oameni) {
		this.scut = scut;
		this.resurse = resurse;
		this.oameni = oameni;
	}

	public Clasa getScut() {
		return scut;
	}

	public void setScut(Clasa scut) {
		this.scut = scut;
	}

	public Clasa getResurse() {
		return resurse;
	}

	public void setResurse(Clasa resurse) {
		this.resurse = resurse;
	}

	public Clasa getOameni() {
		return oameni;
	}

	public void setOameni(Clasa oameni) {
		this.oameni = oameni;
	}

}
