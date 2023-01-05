package me.minutz.trv.eng.handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

import me.minutz.trv.eng.firework.BubbleSpark;
import me.minutz.trv.eng.firework.CircleSpark;
import me.minutz.trv.eng.firework.GiantSpark;
import me.minutz.trv.eng.firework.MovingSpark;
import me.minutz.trv.eng.firework.Spark;
import me.minutz.trv.eng.firework.TrigSpark;


public class FireworksPanel{
	
	public LinkedList<Spark> sparks = new LinkedList<Spark>();
	
	
	private Random generator = new Random();
	
	public FireworksPanel() {
		System.out.println("FIREWORKSYSTEM LOADED!");
	}
	
	public int sparksLeft() {
		return sparks.size();
	}
	
	public boolean removeSpark(Spark s) {
		return this.sparks.remove(s);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		Spark array[] = sparks.toArray(new Spark[0]);
		
		for(Spark s : array) {
			s.draw(g2d);
		}
	}

	public void explode(int x, int y) {
		int sparkCount = 50 + generator.nextInt(15);
		Color c = new Color(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255));
		long lifespan = 1000 + generator.nextInt(100);
		
		int choice = generator.nextInt(100);

		if (choice < 18) {
			createCircleSpark(x, y, sparkCount, c, lifespan);
		} else if (choice < 36) {
			createCircleSpark(x, y, sparkCount, c, lifespan);
		} else if (choice < 54) {
			createMovingSpark(x, y, sparkCount, c, lifespan);
		} else if (choice < 72) {
			createBubbleSpark(x, y, sparkCount, c, lifespan);
		} else{
			createTrigSpark(x, y, sparkCount, c, lifespan);
		}

	}
	
	private void createCircleSpark(int x, int y, int sparkCount, Color c, long lifespan) {
		for (int i = 0; i < sparkCount; i++) {
			double direction = 360 * generator.nextDouble();
			double speed = 10 * generator.nextDouble() + 5;
			sparks.addLast(new CircleSpark(this, direction, x, y, c, lifespan, speed));
		}
	}
	
	
	private void createGiantSpark(int x, int y, int sparkCount, Color c, long lifespan) {
		for (int i = 0; i < sparkCount; i++) {
			double direction = 360 * generator.nextDouble();
			double speed = 10 * generator.nextDouble() + 5;
			sparks.addLast(new GiantSpark(this, direction, x, y, c, lifespan, speed));
		}
	}
	
	private void createTrigSpark(int x, int y, int sparkCount, Color c, long lifespan) {
		for (int i = 0; i < sparkCount; i++) {
			double direction = 360 * generator.nextDouble();
			double speed = 10 * generator.nextDouble() + 5;
			sparks.addLast(new TrigSpark(this, direction, x, y, c, lifespan, speed));
		}
	}
	
	private void createMovingSpark(int x, int y, int sparkCount, Color c, long lifespan) {
		for (int i = 0; i < sparkCount; i++) {
			double direction = 360 * generator.nextDouble();
			double speed = 10 * generator.nextDouble() + 5;
			sparks.addLast(new MovingSpark(this, direction, x, y, c, lifespan, speed));
		}
	}
	
	private void createBubbleSpark(int x, int y, int sparkCount, Color c, long lifespan) {
		for (int i = 0; i < sparkCount; i++) {
			double direction = 360 * generator.nextDouble();
			double speed = 10 * generator.nextDouble() + 5;
			sparks.addLast(new BubbleSpark(this, direction, x, y, c, lifespan, speed));
		}
	}
	

}
