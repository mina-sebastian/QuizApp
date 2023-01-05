package me.minutz.trv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.minutz.trv.eng.etc.HWindow;
import me.minutz.trv.net.KClient;

public class MainMinutz {
	public static int scut=100,res=100,oam=100;
	public static KClient client = null;
	public static boolean isIn = true,load=false,start=false,connected=false,aratat=false,b=true;
	public static String raspuns = "",uname=null;
	public static HWindow hw;

	
	public static void main(String[] args) {
		String mac = getMAC();		
		if(mac != null){
			
			client = new KClient();

			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(new Runnable(){
				@Override
				public void run() {	
				}
			}, 1, 1, TimeUnit.MILLISECONDS);
	  }
	}
	
	   public static String getMAC(){
	       String command = "ipconfig /all";
	       Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	       BufferedReader inn = new BufferedReader(new InputStreamReader(p.getInputStream()));
	       Pattern pattern = Pattern.compile(".*Physical Addres.*: (.*)");
	       while (true) {
	            String line = null;
				try {
					line = inn.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    if (line == null){
		        break;
		    }
		    Matcher mm = pattern.matcher(line);
		    if (mm.matches()) {
		        return mm.group(1);
		    }
		}
			return null;
		   }
}
