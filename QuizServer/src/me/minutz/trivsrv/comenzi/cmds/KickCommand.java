package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.net.PlayerMP;
import me.minutz.trivsrv.net.packets.Packet03Kick;
import me.minutz.trivsrv.util.PlayerUtil;

public class KickCommand extends Command{

	public KickCommand() {
		super("kick");
	}

	@Override
	public String execute(String[] args) {
		PlayerMP p = PlayerUtil.getPlayerMPByNume(args[0]);
		if(p != null){
			p.sendPacket(new Packet03Kick("Ai fost data afara"));
			return "Succes";
		}
		return "Playerul nu a fost gasit!";
	}

	@Override
	public String execute() {
		return "FORMAT INCORECT: "+help();
	}

	@Override
	public String help() {
		return "Da un player afara cu /kick PLAYER";
	}

}
