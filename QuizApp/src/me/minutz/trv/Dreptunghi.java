package me.minutz.trv;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import me.minutz.trv.eng.Game;
import me.minutz.trv.eng.etc.Obiect;
import me.minutz.trv.eng.etc.ObjT;
import me.minutz.trv.eng.etc.Text;

public class Dreptunghi extends Obiect{

	private Color c;
	private int w,h,procent,toproc;
	private Text tnume,tproc;
	public Dreptunghi(int x, int y, int wi, int he, String nume, Color col) {
		super(x, y, nume, ObjT.Dreptunghi);
		w=wi;
		h=he;
		c=col;
		procent=0;
	}
	
	private Text getmidtxt(String s,int x,int y,int t,Color c,int font,int size){
		Font f = new Font("arial",font,size);
		Canvas cnv = new Canvas();
	    FontMetrics fm = cnv.getFontMetrics(f);
	    int sw = fm.stringWidth(s);
		Text txt = new Text(s, x-sw/2, y, f, t, c);
		return txt;
	}

	@Override
	public void tick() {
		if(nume.equals("Defensiva")){
			if(toproc != 100-MainMinutz.scut){
				setProcent(MainMinutz.scut);
			}
		}
		if(nume.equals("Oameni")){
			if(toproc != 100-MainMinutz.oam){
				setProcent(MainMinutz.oam);
			}
		}
		if(nume.equals("Resurse")){
			if(toproc != 100-MainMinutz.res){
				setProcent(MainMinutz.res);
			}
		}
		if(toproc>procent){
			if(toproc-procent>50){
				procent+=2;
			}else{
			procent++;
			}
		}
		if(toproc<procent){
			if(procent-toproc>50){
				procent-=2;
			}else{
			procent--;
			}
		}
		if(Game.fer==null){
		tnume=getmidtxt(getNume(), x+w/2, y+h/2,2, Color.white, Font.BOLD, 20);
		tproc=getmidtxt(100-procent+"%", x+w/2, y+h/2+20,2, Color.white, Font.BOLD, 20);
		}
	}

	@Override
	public void render(Graphics g) {
		if(Game.fer==null){
		g.setColor(Game.border);
		int percent = (int)(procent/100.0f*h);
		g.fillRect(x-10, y-20, w+20, h+20);
		g.setColor(c);
		g.fillRect(x, y-10+percent, w, h-percent);
		tnume.render(g);
		tproc.render(g);
		}
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

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int getProcent() {
		return procent;
	}

	public void setProcent(int procent) {
		this.toproc = 100-procent;
	}

}
