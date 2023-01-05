package me.minutz.trv.fereastra;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import me.minutz.trv.eng.Game;
import me.minutz.trv.eng.etc.Text;

public abstract class Fereastra{
	public Rectangle fer;
	public Rectangle x;
	public Rectangle td;
	public Text titlu;
	public String nume;
	
	public Fereastra(String num) {
		nume=num;
		fer=new Rectangle(10,10,Game.getLatime()-20,Game.getInaltime()-20);
		x=new Rectangle((int)(fer.getX()+fer.getWidth()-15),(int)fer.getY(),15,15);
		td=new Rectangle((int)fer.getX()+10, (int)(fer.getY()+x.getY()+x.getHeight()+5), (int)(fer.getWidth()-20), (int)(fer.getHeight()-(fer.getY()+x.getY()+x.getHeight()+5)));
	}

	public void tickk() {
		tick();
		titlu=amt(nume,(int)(fer.getX()+fer.getWidth()/2),(int) (fer.getY()+22),20,Color.WHITE,Font.BOLD,20);
	}
	
	public Text amt(String s,int x,int y,int t,Color c,int font,int size){
		Font f = new Font("arial",font,size);
		Canvas cnv = new Canvas();
	    FontMetrics fm = cnv.getFontMetrics(f);
	    int sw = fm.stringWidth(s);
		Text txt = new Text(s, x-sw/2, y, f, t, c);
		return txt;
	}	

	public void renderr(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)td.getX(), (int)td.getY(), (int)(td.getWidth()), (int)(td.getHeight()));
		render(g);
		g.setColor(Game.bgc);
		g.fillRect(0, 0, Game.LATIME, (int) fer.getY());
		g.fillRect((int)0, (int)(fer.getY()+fer.getHeight()), (int)(Game.LATIME), (int)(fer.getY()+fer.getHeight()));
		g.fillRect(0, 0, (int) (fer.getX()), Game.INALTIME);
		g.fillRect((int) (fer.getX()+fer.getWidth()), (int)fer.getY(), Game.LATIME, Game.INALTIME);
		g.setColor(Game.border);
		g.fillRect((int)fer.getX(), (int)fer.getY(), (int) fer.getWidth(), (int) (fer.getY()+x.getY()+x.getHeight()-4));
		g.fillRect((int)fer.getX(), (int)(td.getY()), (int)(td.getX()-fer.getX()), (int) (td.getHeight()));
		g.fillRect((int)(fer.getX()+fer.getWidth()-10), (int)(td.getY()), (int)(td.getX()-fer.getX()), (int) (td.getHeight()));
		g.fillRect((int)(td.getX()-fer.getX()), (int) (td.getHeight()+td.getY()), (int)fer.getWidth(), (int)(Game.INALTIME-fer.getHeight()-10));
		drawX(g);
		if(titlu!=null){
		titlu.render(g);
		}
	}
	
	public void drawX(Graphics g){
		g.setColor(new Color(214,40,56));
		g.fillRect((int)x.getX(), (int)x.getY(), (int)x.getWidth(), (int)x.getHeight());
		g.setColor(Color.WHITE);
		g.drawLine((int)x.getX()+2, (int)x.getY()+2, (int)(x.getX()+x.getWidth()-4), (int)(x.getY()+x.getHeight()-4));
		g.drawLine((int)x.getX()+2, (int)(x.getY()+x.getHeight()-4), (int)(x.getX()+x.getWidth()-4), (int)(x.getY()+2));
	}
	
	public void clickk(Point2D point){
		if(x.contains(point)){
			Game.fer=null;
			return;
		}
		click(point);
	}
	
	public void suss(){
		sus();
	}
	
	public void joss(){
		jos();
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void click(Point2D point);
	public abstract void sus();
	public abstract void jos();

}
