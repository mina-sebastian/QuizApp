package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.net.PlayerMP;

public class ListCommand extends Command{

	public ListCommand() {
		super("list");
	}

	@Override
	public String execute(String[] args) {
		return execute();
	}

	@Override
	public String execute() {
		String f = Maine.server.connectedPlayers.size()+" playeri online:";
		for(PlayerMP p : Maine.server.connectedPlayers){
			f=f+""+p.getCont().getNume();
			f=f+", ";
		}
		return f;
	}

	@Override
	public String help() {
		return "Vezi lista de playeri";
	}

}
