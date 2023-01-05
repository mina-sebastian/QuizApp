package me.minutz.trivsrv.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.file.FileConfiguration;
import org.yaml.snakeyaml.file.YamlConfiguration;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.joc.macaroane.intreb.AIntrebare;
import me.minutz.trivsrv.joc.macaroane.intreb.BIntrebare;
import me.minutz.trivsrv.joc.macaroane.intreb.Categ;
import me.minutz.trivsrv.joc.macaroane.intreb.ITip;
import me.minutz.trivsrv.joc.macaroane.intreb.Intrebare;
import me.minutz.trivsrv.useri.Clasa;
import me.minutz.trivsrv.useri.Cont;
import me.minutz.trivsrv.useri.Status;
import me.minutz.trivsrv.util.CryptoUtil;

public class Config {
	  public static File userFile;
	  public static FileConfiguration user;
	  public static File intrFile;
	  public static FileConfiguration intr;
	  public static File sFile;
	  public static FileConfiguration serv;
	  
	  
	  private static void copy(InputStream in, File file)
	  {
	    try
	    {
	      OutputStream out = new FileOutputStream(file);
	      byte[] buffer = new byte[63];
	      int len;
	      while ((len = in.read(buffer)) > 0)
	      {
	        out.write(buffer, 0, len);
	      }
	      out.close();
	      in.close();
	    }
	    catch (Exception localException)
	    {
	    }
	  }
	public static void load(){
	    File f = new File(System.getProperty("user.dir")+"\\serverConfig\\conturi");
	    if(!f.exists()){
	    	f.mkdir();
	    }
		
		  userFile = new File(System.getProperty("user.dir")+"\\serverConfig", "useri.yml");
	      user = new YamlConfiguration();
		  
		    if (!userFile.exists())
		    {
		      userFile.getParentFile().mkdirs();
		      copy(null, userFile);
		    }
		    
		      try {
				user.load(userFile);
				} catch (Exception e2) {
				e2.printStackTrace();
			}
		      
			  intrFile = new File(System.getProperty("user.dir")+"\\serverConfig", "intrebari.yml");
		      intr = new YamlConfiguration();
			  
			    if (!intrFile.exists())
			    {
			      intrFile.getParentFile().mkdirs();
			      copy(null, intrFile);
			    }
			    
			      try {
					intr.load(intrFile);
					} catch (Exception e2) {
					e2.printStackTrace();
				}
			      
				  sFile = new File(System.getProperty("user.dir")+"\\serverConfig", "server.yml");
			      serv = new YamlConfiguration();
				  
				    if (!sFile.exists())
				    {
				      sFile.getParentFile().mkdirs();
				      copy(null, sFile);
				    }
				    
				      try {
						serv.load(sFile);
						} catch (Exception e2) {
						e2.printStackTrace();
					}
	}
	
	public static void saveUser(){
		try {
			user.save(userFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveIntr(){
		try {
			intr.save(intrFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveServer(){
		try {
			serv.save(sFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
