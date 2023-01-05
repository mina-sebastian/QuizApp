package me.minutz.trivsrv.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.file.FileConfiguration;
import org.yaml.snakeyaml.file.YamlConfiguration;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.useri.Clasa;
import me.minutz.trivsrv.useri.Cont;
import me.minutz.trivsrv.useri.Status;
import me.minutz.trivsrv.util.CryptoUtil;

public class ConturiConfig {
	  public static PConfig getContFile(String uuid){
		  File f = new File(System.getProperty("user.dir")+"\\serverConfig\\conturi", uuid+".yml");
	      YamlConfiguration yc = new YamlConfiguration();
	      return new PConfig(f,yc);
	  }
	  
	  public static boolean exists(String uuid){
		  List<String> l = Config.user.getStringList(Maine.cpath);
		  if(l!=null){
			  if(l.contains(uuid)){
				  return true;
			  }
		  }
		  return false;
	  }
	public static void loadConturi(){
		boolean ntl = false;
		if(Config.users.getStringList(Maine.cpath)!=null){
		Maine.gdfyugd65=Config.users.getStringList(Maine.cpath);
//		if(Maine.getSQL().lista.size()!=Maine.gdfyugd65.size()){
//			ntl=true;
//			for(String uuid:Maine.getSQL().lista){
//				if(!Maine.gdfyugd65.contains(uuid)){
//					Maine.getSQL().delCont(uuid);
//				}
//			}
//		}
		for(String uuid:Maine.gdfyugd65){
			Cont c = getContByUUID(uuid);
			if(c != null){
				Maine.conturi.add(c);
				if(c.isBanat()){
					Maine.banati.add(c);
				}else{
					if(ntl){
						if(!Maine.getSQL().lista.contains(uuid)){
						Maine.getSQL().addCont(uuid, c.getNume(), c.getStatus().getScut().getProcent(), c.getStatus().getResurse().getProcent(), c.getStatus().getOameni().getProcent());
						}
					}
				}
			}else{
				System.out.println(uuid+" nu a fost gasit");
			}
		}
		}else{
			List<String> s = new ArrayList<String>();
			Config.saveUsersList(Maine.cpath,s);
			Maine.gdfyugd65=s;
		}
	}
	
	public static Cont getContsByUUID(String uuid){
		if(exists(uuid)){
			PConfig pconf = getContFile(uuid);
			FileConfiguration user = pconf.getCont();
			CryptoUtil cru = new CryptoUtil();
			String pass = cru.decrypt(Maine.decrK, user.getString("pass"));
			int scut = user.getInt(Maine.scut);
			int resurse = user.getInt(Maine.resurse);
			int oameni = user.getInt(Maine.oameni);
			boolean banat = user.getBoolean("banat");
			Cont c = new Cont(uuid,user.getString("nume"),
					pass,
					new Status(new Clasa(scut,Maine.scut),
							   new Clasa(resurse,Maine.resurse),
							   new Clasa(oameni,Maine.oameni)),
					banat);
			return c;
		}
		return null;
	}
	
	public static void updateConts(Cont c){
		PConfig pconf = getContFile(c.getUUID());
		FileConfiguration user = pconf.getCont();
		user.set("nume", c.getNume());
		CryptoUtil cr = new CryptoUtil();
		String ep = cr.encrypt(Maine.decrK, c.getParola());
		user.set("pass", ep);
		user.set("banat", c.isBanat());
		user.set(c.getStatus().getScut().getTip(), c.getStatus().getScut().getProcent());
		user.set(c.getStatus().getResurse().getTip(), c.getStatus().getResurse().getProcent());
		user.set(c.getStatus().getOameni().getTip(), c.getStatus().getOameni().getProcent());
//		Maine.getSQL().updateCont(c.getUUID(), c.getStatus().getScut().getProcent(), c.getStatus().getResurse().getProcent(), c.getStatus().getOameni().getProcent());
		pconf.save();
	}
	
	public static void addConts(Cont c){
		PConfig pconf = getContFile(c.getUUID());
		FileConfiguration user = pconf.getCont();
		user.set("nume", c.getNume());
		CryptoUtil cr = new CryptoUtil();
		String ep = cr.encrypt(Maine.decrK, c.getParola());
		user.set("pass", ep);
		user.set("banat", c.isBanat());
		user.set(c.getStatus().getScut().getTip(), c.getStatus().getScut().getProcent());
		user.set(c.getStatus().getResurse().getTip(), c.getStatus().getResurse().getProcent());
		user.set(c.getStatus().getOameni().getTip(), c.getStatus().getOameni().getProcent());
		pconf.save();
		  List<String> l = Config.user.getStringList(Maine.cpath);
		  if(l!=null){
			  l.add(c.getUUID());
			  Config.user.set(Maine.cpath, l);
		  }else{
			  l = new ArrayList<>();
			  l.add(c.getUUID());
			  Config.user.set(Maine.cpath, l);
		  }
		  Config.saveUser();
//		Maine.getSQL().addCont(c.getUUID(), c.getNume(), c.getStatus().getScut().getProcent(), c.getStatus().getResurse().getProcent(), c.getStatus().getOameni().getProcent());
	}
	
	public static String getContUUIDByUser(String user){
		return Config.user.getString("cu."+user);
	}
	public static void setUserContUUID(String user,String uuid){
		  List<String> l = Config.user.getStringList(Maine.cpath);
		  if(l!=null){
			  l.add(user);
			  Config.user.set(Maine.upath, l);
		  }else{
			  l = new ArrayList<>();
			  l.add(user);
			  Config.user.set(Maine.upath, l);
		  }
		Config.user.set("cu."+user,uuid);
		Config.saveUser();
	}
	
	public static Cont getContByNume(String nume){
		  List<String> l = Config.user.getStringList(Maine.cpath);
		  if(l!=null){
			  for(String uuid:l){
				  
			  }
		  }
		  return null;
	}
}
