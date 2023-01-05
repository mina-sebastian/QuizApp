package me.minutz.trivsrv.comenzi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import me.minutz.trivsrv.comenzi.cmds.AddIntrCommand;
import me.minutz.trivsrv.comenzi.cmds.BanCommand;
import me.minutz.trivsrv.comenzi.cmds.Command;
import me.minutz.trivsrv.comenzi.cmds.HelpCommand;
import me.minutz.trivsrv.comenzi.cmds.KickCommand;
import me.minutz.trivsrv.comenzi.cmds.ListCommand;
import me.minutz.trivsrv.comenzi.cmds.LogoutCommand;
import me.minutz.trivsrv.comenzi.cmds.SendPacketCommand;
import me.minutz.trivsrv.comenzi.cmds.SetCommand;
import me.minutz.trivsrv.comenzi.cmds.UnbanCommand;

public class Consola implements Runnable{
	Scanner input = null;
	public static List<Command> cmds = new ArrayList<Command>();
	private static SimpleDateFormat sdf;
	public static void display(String mesaj){
			String time = sdf.format(new Date()) + ": " + mesaj;
			System.out.println(time);
	}
	public static String getDisplay(String mesaj){
		String time = sdf.format(new Date()) + ": " + mesaj;
		return time;
	}
	public Consola(){
		sdf = new SimpleDateFormat("HH:mm:ss");
		input = new Scanner(System.in);
		
		cmds.add(new HelpCommand());
		cmds.add(new SetCommand());
		cmds.add(new LogoutCommand());
		cmds.add(new BanCommand());
		cmds.add(new UnbanCommand());
		cmds.add(new KickCommand());
		cmds.add(new AddIntrCommand());
		cmds.add(new SendPacketCommand());
		cmds.add(new ListCommand());
	}
	public static Command getCommand(String cmd){
		for(Command c:cmds){
			if(c.getCmd().equalsIgnoreCase(cmd)){
				return c;
			}
		}
		return null;
	}
	@Override
	public void run() {
	    while (true) {
	    	String s = input.nextLine();
	    	if(!s.replace(" ", "").isEmpty()){
	    	String comanda = "";
	    	String[] args = null;
	    	if(s.contains(" ")){
	    		String[] c = s.split(" ");
	    		comanda = c[0].toLowerCase();
	    		args = new String[c.length-1];
	    	for(int i=1;i<c.length;i++){
	    		args[i-1]=c[i];
	    	}
	    	}else{
	    		comanda = s.toLowerCase();
	    	}
	    	Command c = getCommand(comanda);
	    	if(c != null){
	    	if(args != null){
	    		try{
	    		display(c.execute(args)+"\n");
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	      }else{
	    	  try{
	    	  display(c.execute()+"\n");
	    	  }catch(Exception e){
	    			e.printStackTrace();
	    		}
	      }
	    	}else{
	    		display("Comanda nu a fost gasita!");
	    	}
	      }else{
	    	  display("Comanda nu poate fi goala!");
	      }
	    }
	}

}
