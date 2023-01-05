package me.minutz.trv.eng.etc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.minutz.trv.Buton;
import me.minutz.trv.MainMinutz;
import me.minutz.trv.eng.Game;
import me.minutz.trv.eng.handler.Handler;
import me.minutz.trv.fereastra.Fereastra;

public class MouseInput implements MouseListener{
	
	private Handler h;
	
	public MouseInput(Handler ha){
		System.out.println("MOUSELISTENER LOADED!");
		h=ha;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		MainMinutz.isIn=true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		MainMinutz.isIn=false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(Game.fer!=null){
				Fereastra b = Game.fer;
				if(b.fer.contains(arg0.getPoint())){
					b.clickk(arg0.getPoint());
					return;
				}
		}
		for(Obiect o:Game.getHndl().onbj){
			if(o.getTip()==ObjT.Buton){
				Buton b = (Buton) o;
				if(b.buton.contains(arg0.getPoint())){
					b.click();
					return;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
