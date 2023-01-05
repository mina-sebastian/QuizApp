package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.comenzi.Consola;

public class HelpCommand extends Command{

	public HelpCommand() {
		super("help");
	}

	@Override
	public String execute(String[] args) {
		Command c = Consola.getCommand(args[0]);
		if(c != null){
			Consola.display("-----------------");
			Consola.display(c.getCmd()+" - "+c.help());
		return "-----------------";
		}
		return "Comanda "+args[0]+" nu a fost gasita";
	}

	@Override
	public String execute() {
		Consola.display("-----------------");
		for(Command c:Consola.cmds){
			Consola.display(c.getCmd()+" - "+c.help());
		}
		return "-----------------";
	}

	@Override
	public String help() {
		return "Cauta ajutor.";
	}

}
