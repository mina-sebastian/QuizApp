package me.minutz.trivsrv.comenzi.cmds;

import me.minutz.trivsrv.net.Network.DPacket;
import me.minutz.trivsrv.net.PlayerMP;
import me.minutz.trivsrv.util.PlayerUtil;

public class SendPacketCommand extends Command{

	public SendPacketCommand() {
		super("sendpacket");
	}

	@Override
	public String execute(String[] args) {
		PlayerMP p = PlayerUtil.getPlayerMPByNume(args[0]);
		if(p!=null){
			
		String data = args[1];
		
		for(int i=2;i<args.length;i++){
			if(i+1>=args.length){
				data=data+args[i];	
			}else{
			data=data+args[i]+",";
			}
		}
		DPacket d = new DPacket();
		d.data=data.getBytes();
		p.getConnection().sendTCP(d);
		
		return "Packet trimis!";
		}
		
		return "Playerul nu este online!";
	}

	@Override
	public String execute() {
		return "FORMAT INCORECT "+help();
	}

	@Override
	public String help() {
		return "Scrie /sendpacket <player> <packetID> <data1> <data2> <data3> <data4> <data5>";
	}

}
