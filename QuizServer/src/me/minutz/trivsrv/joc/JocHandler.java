package me.minutz.trivsrv.joc;

import java.util.ArrayList;
import java.util.List;

import me.minutz.trivsrv.joc.vars.Joc;

public class JocHandler {
	
	private List<Joc> jocuri;
	
	public JocHandler(){
		jocuri = new ArrayList<Joc>();
	}
	
	public void addJoc(Joc j){
		jocuri.add(j);
	}
	
	public void remJoc(Joc j){
		jocuri.remove(j);
	}
	
}
