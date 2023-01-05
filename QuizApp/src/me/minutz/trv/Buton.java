package me.minutz.trv;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.minutz.trv.eng.Game;
import me.minutz.trv.eng.etc.Obiect;
import me.minutz.trv.eng.etc.ObjT;
import me.minutz.trv.eng.etc.Text;

public abstract class Buton extends Obiect{

	int w,h,l=0;
	public Rectangle buton;
	Color col = Game.butonCol;
	Text t;
	
	public Buton(int x, int y, String nume) {
		super(x, y, nume, ObjT.Buton);
		w=120;
		h=50;
		buton = new Rectangle(x-w/2, y, w, h);
	}

	public Text amt(String s,int x,int y,int t,Color c,int font,int size){
		Font f = new Font("arial",font,size);
		Canvas cnv = new Canvas();
	    FontMetrics fm = cnv.getFontMetrics(f);
	    int sw = fm.stringWidth(s);
		Text txt = new Text(s, x-sw/2, y, f, t, c);
		return txt;
	}
	
	@Override
	public void tick() {
		t=amt(getNume(), x, y+h/2+6,2, Color.white, Font.BOLD, 20);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Game.border);
		g.fillRect(x-w/2-5, y-5, w+10, h+10);
		if(col==Game.bgc){
			if(l<1){
		col=Game.butonCol;
			}else{
				l--;
			}
		}
		g.setColor(col);
		g.fillRect(x-w/2, y, w, h);
		if(t!=null){
		t.render(g);
		}
	}
	public void clickz(){
		if(l<1){
		l=20;
		col=Game.border;
		}
		click();
	}
	
	
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public abstract void click();
}
