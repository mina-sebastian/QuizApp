package me.minutz.trv.eng.etc;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import me.minutz.trv.eng.Game;

public class MouseWListener implements MouseWheelListener{
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
    	if(Game.fer!=null){
            if (e.getWheelRotation() < 0){Game.fer.suss();}else{Game.fer.joss();}
    	}

    }
}
