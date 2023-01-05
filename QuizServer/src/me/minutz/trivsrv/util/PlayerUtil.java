package me.minutz.trivsrv.util;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.net.PlayerMP;

public class PlayerUtil {
	public static PlayerMP getPlayerMPByNume(String nume){
		for(PlayerMP p:Maine.server.connectedPlayers){
			if(p.getCont().getNume().toLowerCase().equals(nume.toLowerCase())){
				return p;
			}
		}
		return null;
	}
	
	public static PlayerMP getPlayerMPByUUID(String uuid){
		for(PlayerMP c:Maine.server.connectedPlayers){
			if(c.getCont().getUUID().equals(uuid)){
				return c;
			}
		}
		return null;
	}
	
	public static PlayerMP getPlayerMPByMAC(String mac){
		for(PlayerMP c:Maine.server.connectedPlayers){
			if(c.getMac().equals(mac)){
				return c;
			}
		}
		return null;
	}
}
