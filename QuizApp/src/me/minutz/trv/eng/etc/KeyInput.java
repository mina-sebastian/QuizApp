package me.minutz.trv.eng.etc;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.minutz.trv.eng.handler.Handler;


public class KeyInput extends KeyAdapter{
	
	private Handler h;
	public static String mesaj= "";
	public static boolean chat=false,sfps=false;
	public static int v = 2;
	
	public KeyInput(Handler ha){
		System.out.println("KEYSYSTEM LOADED!");
		h=ha;
	}

	public void keyPressed(KeyEvent e){
		int in = e.getKeyCode();
		if(!chat){

		}
	}
	
	public void keyReleased(KeyEvent e){
		int in = e.getKeyCode();
		
		if(in==KeyEvent.VK_F){
		if(sfps){
			sfps=false;
		}else{
			sfps=true;
		}
		}
		if(in==KeyEvent.VK_SLASH){
			if(chat){
				chat=false;
				System.out.println("Cmd off");
			}else{
				chat=true;
				System.out.println("Cmd on");
			}
		}
		
		if(!chat){

		}
	}
	
	
	
}
