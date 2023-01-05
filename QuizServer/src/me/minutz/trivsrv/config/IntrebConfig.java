package me.minutz.trivsrv.config;

import java.util.ArrayList;
import java.util.List;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.joc.macaroane.intreb.AIntrebare;
import me.minutz.trivsrv.joc.macaroane.intreb.BIntrebare;
import me.minutz.trivsrv.joc.macaroane.intreb.Categ;
import me.minutz.trivsrv.joc.macaroane.intreb.ITip;
import me.minutz.trivsrv.joc.macaroane.intreb.Intrebare;

public class IntrebConfig {

	public static void loadIntr(){
		if(Config.intr.getStringList(Maine.ipath)!=null){
		Maine.intrList=Config.intr.getStringList(Maine.ipath);
		for(String id:Maine.intrList){
			Intrebare i = getIntrByID(id);
			if(i!=null){
				Maine.intr.add(i);
			}
		}
		}else{
			List<String> s = new ArrayList<String>();
			Config.intr.set(Maine.ipath,s);
			Maine.intrList=s;
		}
	}
	
	public static Intrebare getIntrByID(String id){
		if(Config.intr.getString("inrebari."+id+".intr")!=null){
			if(Config.intr.getString("inrebari."+id+".tip").equals("a")){
				  return new AIntrebare(Config.intr.getString("inrebari."+id+".intr"),
										Config.intr.getString("inrebari."+id+".rasp"),
										Categ.getByString("inrebari."+id+".categ"));
			}else{
				  return new BIntrebare(Config.intr.getString("inrebari."+id+".intr"),
						  				Config.intr.getString("inrebari."+id+".rasp"),
						  				Config.intr.getString("inrebari."+id+".rasp2"),
						  				Config.intr.getString("inrebari."+id+".rasp3"),
						  				Config.intr.getString("inrebari."+id+".rasp4"),
						  				Categ.getByString("inrebari."+id+".categ"));				
			}
		}
		return null;
	}
	
	public static void addIntr(Intrebare i){
		String id = i.getIntrebare().toLowerCase().replace("?", "").replace(" ", "").replace(".", "")+i.getTip().toString();
		if(i.getTip()==ITip.d){
			Config.intr.set("inrebari."+id+".tip", "a");
			Config.intr.set("inrebari."+id+".categ", i.getCateg().getString());
			Config.intr.set("inrebari."+id+".intr", i.getIntrebare());
			Config.intr.set("inrebari."+id+".rasp", i.getRaspuns());
			Maine.intrList.add(id);
			Config.intr.set(Maine.ipath, Maine.intrList);
			Config.saveIntr();
		}else{
			BIntrebare b = (BIntrebare)i;
			Config.intr.set("inrebari."+id+".tip", "b");
			Config.intr.set("inrebari."+id+".categ", i.getCateg().getString());
			Config.intr.set("inrebari."+id+".intr", i.getIntrebare());
			Config.intr.set("inrebari."+id+".rasp", i.getRaspuns());
			Config.intr.set("inrebari."+id+".rasp2", b.getVar2());
			Config.intr.set("inrebari."+id+".rasp3", b.getVar3());
			Config.intr.set("inrebari."+id+".rasp4", b.getVar4());
			Maine.intrList.add(id);
			Config.intr.set(Maine.ipath, Maine.intrList);
			Config.saveIntr();
		}
		Maine.intr.add(getIntrByID(id));
	}
	
}
