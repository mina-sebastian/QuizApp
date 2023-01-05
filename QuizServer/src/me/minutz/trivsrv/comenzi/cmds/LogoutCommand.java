package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.config.Config;
import me.minutz.trivsrv.config.ConturiConfig;
import me.minutz.trivsrv.net.PlayerMP;
import me.minutz.trivsrv.net.packets.Packet03Kick;
import me.minutz.trivsrv.useri.Cont;
import me.minutz.trivsrv.util.PlayerUtil;

public class LogoutCommand extends Command{

	public LogoutCommand() {
		super("logout");
	}

	@Override
	public String execute(String[] args) {
		int h = 0;
		for(String user:Maine.userList){
			if(ConturiConfig.getContUUIDByUser(user)!=null){
				Cont c = ConturiConfig.getContByUUID(ConturiConfig.getContUUIDByUser(user));
				if(c.getNume().equals(args[0])){
					Config.setUserContUUID(user, null);
					h++;
				}
			}
		}
		PlayerMP p = PlayerUtil.getPlayerMPByNume(args[0]);
		if(p != null){
			Config.setUserContUUID(p.getMac(), null);
			p.sendPacket(new Packet03Kick("Ai fost delogat de catre server!"));
		}
		if(h!=1){
		return h+" playeri au fost delogati!";
		}else{
			return "1 player a fost delogat!";
		}
	}

	@Override
	public String execute() {
		return "FORMAT INCORECT: "+help();
	}

	@Override
	public String help() {
		return "Delogheaza un player cu:/logout PLAYER";
	}

}
