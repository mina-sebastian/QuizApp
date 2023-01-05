package me.minutz.trv.eng.etc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import me.minutz.trv.eng.Game;

public class Text {
	private String str;
	private int x,y;
	private Font font;
	private int timp;
	private Color color;
	private int i=0;
	
	public Text(String str, int x, int y, Font font, int timp, Color color) {
		super();
		this.str = str;
		this.x = x;
		this.y = y;
		this.font = font;
		this.timp = timp;
		this.color = color;
	}
	
	public void tick(){
		if(i<=timp){
		i++;
		}else{
			Game.getTHndl().remText(this);
		}
	}
	
	public void render(Graphics g){
		if(i<timp){
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(getFont());
		g.setColor(getColor());
		g.drawString(getStr(), getX(), getY());
		}
	}

	public String getStr() {
		return str;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Font getFont() {
		return font;
	}

	public int getTimp() {
		return timp;
	}

	public Color getColor() {
		return color;
	}
	

}
