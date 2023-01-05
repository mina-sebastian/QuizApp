package me.minutz.trivsrv.joc.runnable;

import java.util.ArrayList;
import java.util.List;

public class MinRunnable implements Runnable{
	public List<MinRun> minr = new ArrayList<MinRun>();
	public MinRunnable(){
	}
	@Override
	public void run() {
		for(MinRun m:minr){
			if(m.stop){
				minr.remove(m);
				continue;
			}else{
				if(m.idelay){
					m.i++;
					if(m.i>m.delay){
					m.run();
					minr.remove(m);
					continue;
				}
				}else{
					m.run();
				}
			}
		}
	}

}
