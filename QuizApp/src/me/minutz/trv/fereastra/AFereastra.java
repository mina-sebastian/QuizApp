package me.minutz.trv.fereastra;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class AFereastra extends Fereastra{
	public AFereastra() {
		super("Ataca");
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
//		drawBranch(g2d,td.getX()+td.getWidth()/2.0,(double)td.getY()+td.getHeight()-5, Math.PI/2, 120, 10);
	}

	@Override
	public void click(Point2D point) {

	}

	@Override
	public void sus() {
	}

	@Override
	public void jos() {
		// TODO Auto-generated method stub
		
	}

}
