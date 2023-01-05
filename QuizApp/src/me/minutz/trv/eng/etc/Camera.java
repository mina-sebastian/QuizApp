package me.minutz.trv.eng.etc;

import me.minutz.trv.eng.Game;

public class Camera {
	
	private float x,y;
	
	public Camera(float xu,float yu){
		x=xu;
		y=yu;
	}
	
	public void tick(float xx,float yy){
		x=-xx+Game.LATIME/2;
		y=-yy+Game.INALTIME/2;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	

}
