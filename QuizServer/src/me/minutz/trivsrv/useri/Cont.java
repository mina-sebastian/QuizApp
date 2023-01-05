package me.minutz.trivsrv.useri;

public class Cont {
	
	public String nume,parola,uuid;
	public Status status;
	public boolean banat=false;

	public Cont(String uuid, String nume, String parola, Status status, boolean banat) {
		super();
		this.nume = nume;
		this.parola = parola;
		this.uuid = uuid;
		this.status = status;
		this.banat = banat;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getUUID() {
		return uuid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isBanat() {
		return banat;
	}

	public void setBanat(boolean banat) {
		this.banat = banat;
	}
	
	
}
