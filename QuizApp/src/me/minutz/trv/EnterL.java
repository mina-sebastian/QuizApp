package me.minutz.trv;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EnterL implements KeyListener{
	public Meniu men;
	public EnterL(Meniu m){
		men=m;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_ENTER){
			men.enterLog();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
