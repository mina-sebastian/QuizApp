package me.minutz.trv.eng.handler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.LinkedList;

import me.minutz.trv.eng.Game;
import me.minutz.trv.eng.etc.Text;



public class TextHandler {
	
	public LinkedList<Text> onbj = new LinkedList<Text>();
	
	public TextHandler(){
		System.out.println("TEXTSYSTEM LOADED!");
	}
	
	public void tick(){
		for(int i =0; i<onbj.size();i++){
			Text gbj;
			gbj = onbj.get(i);
			gbj.tick();
		}
	}
	public void render(Graphics g){
		for(int i =0; i<onbj.size();i++){
			Text gbj;
			gbj = onbj.get(i);
			gbj.render(g);
		}
	}
	
	public void addText(Text t){
		onbj.add(t);
	}
	
	public void remText(Text t){
		if(onbj.contains(t)){
			onbj.remove(t);
		}
	}
	
	public void addText(String s,int x,int y,int t,Color c,int font,int size){
		Font f = new Font("arial",font,size);
		Text txt = new Text(s, x, y, f, t, c);
		addText(txt);
	}
	
	public void addMidText(String s,int x,int y,int t,Color c,int font,int size){
		Font f = new Font("arial",font,size);
		Canvas cnv = new Canvas();
	    FontMetrics fm = cnv.getFontMetrics(f);
	    int sw = fm.stringWidth(s);
		Text txt = new Text(s, x-sw/2, y, f, t, c);
		addText(txt);
	}
	
	public void addTitle(String s,int t,Color c){
		Font f = new Font("arial",Font.BOLD,80);
		Canvas cnv = new Canvas();
	    FontMetrics fm = cnv.getFontMetrics(f);
	    int sw = fm.stringWidth(s);
		Text txt = new Text(s, (int)Game.getLatime()/2-sw/2, (int)Game.getInaltime()/2, f, t, c);
		addText(txt);
	}
	
}
