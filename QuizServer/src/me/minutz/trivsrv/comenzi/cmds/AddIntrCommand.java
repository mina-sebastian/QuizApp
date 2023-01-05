package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.config.Config;
import me.minutz.trivsrv.joc.macaroane.intreb.AIntrebare;
import me.minutz.trivsrv.joc.macaroane.intreb.BIntrebare;
import me.minutz.trivsrv.joc.macaroane.intreb.Categ;
import me.minutz.trivsrv.joc.macaroane.intreb.Dialog;

public class AddIntrCommand extends Command {

	public AddIntrCommand() {
		super("addintr");
	}

	@Override
	public String execute(String[] args) {
		return execute();
		if(args.length==3){
			String intrebare = args[0].replaceAll("%", " ");
			String raspuns = args[1].replaceAll("%", " ");
			Categ c = Categ.getByString(args[2]);
			if(c!=null){
				Config.addIntr(new AIntrebare(intrebare,raspuns,c));
				return "Intrebare adaugata";
			}else{
				return "Categoria nu exista";
			}
		}else{
			if(args.length==6){
				String intrebare = args[0].replaceAll("%", " ");
				String raspuns = args[1].replaceAll("%", " ");
				String var2 = args[2].replaceAll("%", " ");
				String var3 = args[3].replaceAll("%", " ");
				String var4 = args[4].replaceAll("%", " ");
				Categ c = Categ.getByString(args[5]);
				if(c!=null){
					Config.addIntr(new BIntrebare(intrebare,raspuns,var2,var3,var4,c));
					return "Intrebare adaugata";
				}else{
					return "Categoria nu exista";
				}
			}else{
				if(args.length<3){
					return execute();
				}
				return "Pune '%' in loc de spatii";
			}
		}
	}

	@Override
	public String execute() {
		new Dialog(null, 0);
		return "Meniu deschis!";
	}

	@Override
	public String help() {
		return "Adauga o intrebare cu /addintr";
	}

}
