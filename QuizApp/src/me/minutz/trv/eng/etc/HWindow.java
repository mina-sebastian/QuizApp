package me.minutz.trv.eng.etc;

import java.awt.Dimension;

import javax.swing.JFrame;

import me.minutz.trv.eng.Game;

public class HWindow {
	public JFrame frame;
	public HWindow(int w, int h ,String titlu ,Game g){
		
		g.setPreferredSize(new Dimension(w,h));
		g.setMaximumSize(new Dimension(w,h));
		g.setMinimumSize(new Dimension(w,h));
		
		frame = new JFrame(titlu);
		frame.add(g);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		g.start();
	}

}
