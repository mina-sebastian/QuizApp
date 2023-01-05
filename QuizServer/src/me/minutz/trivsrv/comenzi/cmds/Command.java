package me.minutz.trivsrv.comenzi.cmds;

public abstract class Command {
	
	public String cmd;
	
	public Command(String cmd){
		this.cmd = cmd;
	}
	
	
	public String getCmd() {
		return cmd;
	}

	public abstract String execute(String[] args);
	public abstract String execute();
	public abstract String help();

}
