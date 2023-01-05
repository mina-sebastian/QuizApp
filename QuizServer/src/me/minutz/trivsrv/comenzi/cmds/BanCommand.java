package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.config.ConturiConfig;
import me.minutz.trivsrv.net.PlayerMP;
import me.minutz.trivsrv.net.packets.Packet03Kick;
import me.minutz.trivsrv.useri.Cont;
import me.minutz.trivsrv.util.ContUtil;
import me.minutz.trivsrv.util.PlayerUtil;

public class BanCommand extends Command{

	public BanCommand() {
		super("ban");
	}

	@Override
	public String execute(String[] args) {
		Cont c = ContUtil.getContByNume(args[0]);
		if(c != null){
			c.setBanat(true);
			ConturiConfig.updateCont(c);
			PlayerMP p = PlayerUtil.getPlayerMPByUUID(c.getUUID());
			if(p != null){
				p.setCont(c);
				Maine.server.sendPacket(new Packet03Kick("Ai fost interzis pe server!"), p.getConnection());
			}
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
		return "Baneaza un player cu /ban PLAYER";
	}

}
