package me.minutz.trivsrv;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.minutz.trivsrv.comenzi.Consola;
import me.minutz.trivsrv.config.Config;
import me.minutz.trivsrv.config.IntrebConfig;
import me.minutz.trivsrv.config.ServerConfig;
import me.minutz.trivsrv.joc.JocHandler;
import me.minutz.trivsrv.joc.macaroane.intreb.Intrebare;
import me.minutz.trivsrv.joc.runnable.MinRun;
import me.minutz.trivsrv.joc.runnable.MinRunnable;
import me.minutz.trivsrv.mysql.MySQL;
import me.minutz.trivsrv.net.KServer;
import me.minutz.trivsrv.net.SiteConn;

public class Maine {
		public static List<String> intrList=new ArrayList<String>();
		public static List<Intrebare> intr=new ArrayList<Intrebare>();
		public static String upath="useriList",ipath="intrList",cpath="conturiList",decrK="---------------------",scut="Scut",resurse="Resurse",oameni="Oameni";
		public static boolean c = true,tcp=true,whitelist=false,connected=false;
		public static KServer server;
		public static int tcpport=56643,udpport=56644;
		private static MinRunnable run;
		private static JocHandler joch;
		private static MySQL SQL; 
		
		public static void addRun(MinRun m){
			run.minr.add(m);
		}
		
		public static void main(String args[]) throws Exception
	      {			
//			SQL=new MySQL("MinutzDatabase","conturi");
//			connected=SQL.Connect("localhost", "bugman", "root", "Sebyparola20031");
//			
			Config.load();
			ServerConfig.loadServer();
			IntrebConfig.loadIntr();
			
			run = new MinRunnable();
			
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(new Consola(), 1, 1, TimeUnit.MILLISECONDS);
			ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
			scheduler2.scheduleAtFixedRate(run, 1, 1, TimeUnit.MILLISECONDS);
			server = new KServer();
			
			joch = new JocHandler();

			ScheduledExecutorService scheduler6 = Executors.newScheduledThreadPool(1);
			scheduler6.scheduleAtFixedRate(new SiteConn(), 1, 1, TimeUnit.MILLISECONDS);
	      }
		
		public static JocHandler getJocHndl(){
			return joch;
		}
		
		public static MySQL getSQL(){
		return SQL;
		}
		
}
