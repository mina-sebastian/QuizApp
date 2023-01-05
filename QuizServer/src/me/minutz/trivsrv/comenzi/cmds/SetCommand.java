package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.config.ConturiConfig;
import me.minutz.trivsrv.net.PlayerMP;
import me.minutz.trivsrv.net.packets.Packet06Set;
import me.minutz.trivsrv.useri.Clasa;
import me.minutz.trivsrv.useri.Cont;
import me.minutz.trivsrv.useri.Status;
import me.minutz.trivsrv.util.ContUtil;
import me.minutz.trivsrv.util.PlayerUtil;

public class SetCommand extends Command{

	public SetCommand() {
		super("set");
	}

	@Override
	public String execute(String[] args) {
		if(args.length>=3){
		Cont c = ContUtil.getContByNume(args[0]);
		if(c!=null){
			c.setStatus(new Status(new Clasa(Integer.parseInt(args[2]),Maine.scut),new Clasa(Integer.parseInt(args[1]),Maine.resurse),new Clasa(Integer.parseInt(args[3]),Maine.oameni)));
			ConturiConfig.updateCont(c);
			PlayerMP p =PlayerUtil.getPlayerMPByUUID(c.getUUID());
			if(p!=null){
				p.setCont(c);
				p.sendPacket(new Packet06Set(p.getCont().getStatus().getResurse().getProcent(),p.getCont().getStatus().getScut().getProcent(),p.getCont().getStatus().getOameni().getProcent()));
				return "Succes";
				
			}else{
				return "Succes(playerul nu este online)";
			}
		}
		return "Playerul nu a fost gasit";
		}
		return execute();
	}

	@Override
	public String execute() {
		return "FORMAT INCORECT: "+help();
	}

	@Override
	public String help() {
		return "Seteaza statusul unui player cu /set PLAYER RESURSE SCUT OAMENI";
	}

}
