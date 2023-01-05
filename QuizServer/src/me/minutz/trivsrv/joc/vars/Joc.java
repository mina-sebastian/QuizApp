package me.minutz.trivsrv.joc.vars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.joc.vars.intreb.Intrebare;
import me.minutz.trivsrv.joc.runnable.MinRun;
import me.minutz.trivsrv.net.PlayerMP;
import me.minutz.trivsrv.net.packets.Packet05Polo;

public class Joc {
	
	public String uuid;
	public List<Intrebare> intrebari = new ArrayList<Intrebare>();
	public List<PlayerMP> playeri = new ArrayList<PlayerMP>();
	public int maxPlayeri,minPlayeri,nrIntr=7;
	public boolean started,stopped;
	
	public Joc(int minPlayeri,int maxPlayeri){
		uuid = UUID.randomUUID().toString();
		this.maxPlayeri = maxPlayeri;
		this.minPlayeri = minPlayeri;
		int n = 0;
		long seed = System.nanoTime();
		if(nrIntr>Maine.intr.size()){
			nrIntr=Maine.intr.size();
		}
		Collections.shuffle(Maine.intr, new Random(seed));
		for(Intrebare i:Maine.intr){
			if(n<nrIntr){
				intrebari.add(i);
			}else{
				break;
			}
			n++;
		}
	}
	
	public void start(){
		started=true;
		new MinRun(){

			@Override
			public void run() {
				
			}
			
		}.startDelay(1000);
	}
	
	public void stop(){
		for(PlayerMP p:playeri){
			p.setJoc(null);
		}
		started=false;
		stopped=true;
		Maine.getJocHndl().remJoc(this);
	}
	
	public void addPlayer(PlayerMP p){
		playeri.add(p);
		p.setJoc(this);
		if(playeri.size()==minPlayeri){
			if(maxPlayeri==playeri.size()){
				start();
			}
		}else{
			
		}
	}
	
	public void remPlayer(PlayerMP p){
		playeri.remove(p);
		if(p!=null){
		p.setJoc(null);
		}
		if(started){
		if(playeri.size()==1){
			stop();
			}
		}
	}
	
	public int getPlayeri(){
		return playeri.size();
	}
	
	public int getMaxPlayeri(){
		return maxPlayeri;
	}
	public int getMinPlayeri(){
		return minPlayeri;
	}
	
	public String getUUID(){
		return uuid;
	}
	
	public void scmd(PlayerMP p,String cmd,String arg){
		p.sendPacket(new Packet05Polo("martzafoi"+cmd.replace(":", "/2434")+":"+arg.replace(":", "/2434")));
	}
}
