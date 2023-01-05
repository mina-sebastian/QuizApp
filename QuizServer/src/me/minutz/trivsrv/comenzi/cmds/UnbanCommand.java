package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.config.ConturiConfig;
import me.minutz.trivsrv.useri.Cont;
import me.minutz.trivsrv.util.ContUtil;

public class UnbanCommand extends Command{

	public UnbanCommand() {
		super("unban");
	}

	@Override
	public String execute(String[] args) {
		Cont c = ContUtil.getContByNume(args[0]);
		if(c != null){
			c.setBanat(false);
			ConturiConfig.updateConts(c);
			return "Succes!";
		}
		return "Playerul nu a fost gasit!";
	}

	@Override
	public String execute() {
		return "FORMAT INCORECT: "+help();
	}

	@Override
	public String help() {
		return "Debaneaza un player cu /unban PLAYER";
	}

}
