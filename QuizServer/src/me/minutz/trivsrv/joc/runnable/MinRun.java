package me.minutz.trivsrv.joc.runnable;

import me.minutz.trivsrv.Maine;

public abstract class MinRun {
	public boolean stop=false;
	public int i = 0,delay;
	public boolean idelay;
	public abstract void run();
	public void stop(){
		stop=true;
	}
	public void start(){
		Maine.addRun(this);
	}
	public void startDelay(int delay){
		this.delay=delay;
		idelay=true;
		Maine.addRun(this);
	}
	
}
