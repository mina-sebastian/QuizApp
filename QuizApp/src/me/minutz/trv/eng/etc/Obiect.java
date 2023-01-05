package me.minutz.trv.eng.etc;

import java.awt.Graphics;

public abstract class Obiect {
	
	public int x,y;
	public String nume;
	public ObjT tip;
	
	public Obiect(int x, int y, String nume, ObjT tip) {
		this.x = x;
		this.y = y;
		this.nume = nume;
		this.tip = tip;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public ObjT getTip() {
		return tip;
	}

	public void setTip(ObjT tip) {
		this.tip = tip;
	}
	

}
