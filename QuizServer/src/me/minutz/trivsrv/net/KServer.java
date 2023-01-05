package me.minutz.trivsrv.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.config.Config;
import me.minutz.trivsrv.config.ConturiConfig;
import me.minutz.trivsrv.joc.runnable.MinRun;
import me.minutz.trivsrv.net.Network.DPacket;
import me.minutz.trivsrv.net.packets.Packet;
import me.minutz.trivsrv.net.packets.Packet.PacketTypes;
import me.minutz.trivsrv.net.packets.Packet00Login;
import me.minutz.trivsrv.net.packets.Packet01Disconnect;
import me.minutz.trivsrv.net.packets.Packet02Register;
import me.minutz.trivsrv.net.packets.Packet03Kick;
import me.minutz.trivsrv.net.packets.Packet04Check;
import me.minutz.trivsrv.net.packets.Packet05Polo;
import me.minutz.trivsrv.net.packets.Packet06Set;
import me.minutz.trivsrv.useri.Clasa;
import me.minutz.trivsrv.useri.Cont;
import me.minutz.trivsrv.useri.Status;
import me.minutz.trivsrv.util.ContUtil;

public class KServer {
	Server server;
	public static boolean deschis;
	public List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public KServer(){
		server = new Server();
		server.start();
		Network.register(server);
		deschis=true;
		try {
			server.bind(Maine.tcpport, Maine.udpport);
		} catch (IOException e) {
			server.close();
			deschis=false;
			e.printStackTrace();
			System.exit(1);
		}
		if(deschis){
		    server.addListener(new Listener() {
		        public void received (Connection connection, Object object) {
		           if (object instanceof DPacket) {
		              DPacket request = (DPacket)object;
		              try{
		              parsePacket(request.data,connection);
		              }catch(Exception e){
		            	  e.printStackTrace();
		              }
		           }
		        }
		     });
		    server.addListener(new Listener(){
		    	public void disconnected(Connection connection){
		    		for(PlayerMP cp:connectedPlayers){
		    			if(cp.getConnection().getID()==connection.getID()){
		    				if(cp.getJoc()!=null){
		    					cp.getJoc().remPlayer(cp);
		    				}
		    				handleDis(new Packet01Disconnect(cp.getCont().getNume()));
		    				return;
		    			}
		    		}
		    	}
		    });
		}
	}
	
    private void parsePacket(byte[] data,Connection conn) {
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
        default:
        case INVALID:
            break;
        case LOGIN:
            packet = new Packet00Login(data);
            handleLog((Packet00Login) packet,conn,false);
            break;
        case DISCONNECT:
            packet = new Packet01Disconnect(data);
            handleDis((Packet01Disconnect) packet);
            break;
        case REGISTER:
        	packet = new Packet02Register(data);
        	handleReg((Packet02Register) packet,conn);
        	break;
        case CHECK:
        	packet = new Packet04Check(data);
        	handleCheck((Packet04Check) packet,conn);
        	break;
        }
    }
    
    public boolean exista(PlayerMP player) {
        for (PlayerMP p : this.connectedPlayers) {
          if (p.getCont().getNume().equals(player.getCont().getNume())) {
            return true;
          }
        }
        return false;
      }
    
    public void addConnection(PlayerMP player, Packet00Login packet,boolean b) {
        if (!exista(player))
        {
          this.connectedPlayers.add(player);
          sendPacket(packet,player.getConnection());
          sendPacket(new Packet06Set(player.getCont().getStatus().getResurse().getProcent(),player.getCont().getStatus().getScut().getProcent(),player.getCont().getStatus().getOameni().getProcent()),player.getConnection());
          if(!b){
              	Config.setUserContUUID(player.getMac(), player.getCont().getUUID());
          }
          System.out.println("[" + player.getIpAddress().getHostAddress() + ":" + player.getPort() + "] " + 
            packet.getUsername() + " has connected...");
        }else{
        	player.sendPacket(new Packet03Kick("Deja este cineva conectat pe acest cont!"));
        }
      }
    
    public void handleLog(Packet00Login packet,Connection conn,boolean h){
        Cont c = ContUtil.getContByNume(((Packet00Login)packet).getUsername());
        if (c != null) {
          if (c.getParola().equals(((Packet00Login)packet).getPass())) {
            PlayerMP player = new PlayerMP(((Packet00Login)packet).getMac(), conn, c);
            addConnection(player, (Packet00Login)packet, h);
          } else {
          	Packet05Polo p = new Packet05Polo("Parola gresita");
          	sendPacket(p, conn);
          }
        }else{
         Packet05Polo p = new Packet05Polo("Numele nu a fost gasit");
         sendPacket(p, conn);    
        }
    }
    
    private String getUUID(){
		String uuid = UUID.randomUUID().toString();
		if(!ConturiConfig.exists(uuid)){
			return uuid;
		}
		return getUUID();
    }
    
    public void handleReg(Packet02Register packet,Connection conn){
    	String u = packet.getUsername();
    	String p = packet.getPass();
    	if(u.replaceAll(" ", "").isEmpty()){
    		return;
    	}
    	if(p.replaceAll(" ", "").isEmpty()){
    		return;
    	}
    	if(u.length()<11){
    		if(ContUtil.getContByNume(u)==null){
    			String uuid = getUUID();
    			Status stat = new Status(new Clasa(100,Maine.scut),new Clasa(100,Maine.resurse),new Clasa(100,Maine.oameni));
    			ConturiConfig.addCont(new Cont(uuid,u,p,stat,false));
				Packet00Login pk = new Packet00Login(((Packet02Register)packet).getMac(),u,p);
				handleLog(pk,conn,true);
    		}else{
    			sendPacket(new Packet05Polo("Numele deja exista!"), conn);	
    		}
    	}else{
    		sendPacket(new Packet05Polo("Numele trebuie sa aiba cel mult 11 caractere"), conn);
    	}
    }
    
    public void handleCheck(Packet04Check packet,Connection conn){
		if(ConturiConfig.getContUUIDByUser(((Packet04Check)packet).getMac())!=null){
		Cont c = ContUtil.getContByUUID(ConturiConfig.getContUUIDByUser(((Packet04Check)packet).getMac()));
		if(c != null){
			if(!c.isBanat()){
				Packet00Login p = new Packet00Login(((Packet04Check)packet).getMac(),c.getNume(),c.getParola());
				handleLog(p,conn,true);
			}else{
		         Packet03Kick p = new Packet03Kick("Esti banat!");
		         sendPacket(p, conn); 
		         conn.close();
			}
			}
		}else{
			Packet05Polo p = new Packet05Polo("nuexista");
			sendPacket(p,conn);
		}
    }

      public void handleDis(Packet01Disconnect packet)
      {
          System.out.println("[" + getPlayerMP(packet.getUsername()).getIpAddress().getHostAddress() + ":" + getPlayerMP(packet.getUsername()).getPort() + "] "
                  + ((Packet01Disconnect) packet).getUsername() + " has left...");
        this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
       sendAllPacket(packet);
      }

      public PlayerMP getPlayerMP(String username) {
        for (PlayerMP player : this.connectedPlayers) {
          if (player.getCont().getNume().equals(username)) {
            return player;
          }
        }
        return null;
      }
      
      public int getPlayerMPIndex(String username) {
    	    int index = 0;
    	    for (PlayerMP player : this.connectedPlayers) {
    	      if (player.getCont().getNume().equals(username)) {
    	        break;
    	      }
    	      index++;
    	    }
    	    return index;
    	  }
      
      public void sendPacket(Packet p,Connection c){
    	  if(Maine.tcp){
    		  sendTCPPacket(p,c);
    	  }else{
    		  sendUDPPacket(p,c);
    	  }
      }
      public void sendAllPacket(Packet p){
    	  if(Maine.tcp){
    		  sendAllTCPPacket(p);
    	  }else{
    		  sendAllUDPPacket(p);
    	  }
      }
      public void sendAllExcept(Packet p,Connection c){
    	  if(Maine.tcp){
    		  sendAllExceptTCPPacket(p,c);
    	  }else{
    		  sendAllExceptUDPPacket(p,c);
    	  }
      }
      
      private void sendTCPPacket(Packet p,Connection c){
    	  DPacket pk = new DPacket();
    	  pk.data=p.getData();
    	  c.sendTCP(pk);
      }
      private void sendUDPPacket(Packet p,Connection c){
    	  DPacket pk = new DPacket();
    	  pk.data=p.getData();
    	  c.sendUDP(pk);
      }
      
      private void sendAllUDPPacket(Packet p){
    	  DPacket pk = new DPacket();
    	  pk.data=p.getData();
    	  server.sendToAllUDP(pk);
      }
      private void sendAllTCPPacket(Packet p){
    	  DPacket pk = new DPacket();
    	  pk.data=p.getData();
    	  server.sendToAllTCP(pk);
      }
      
      private void sendAllExceptTCPPacket(Packet p,Connection con){
    	  DPacket pk = new DPacket();
    	  pk.data=p.getData();
    	  server.sendToAllExceptTCP(con.getID(), pk);
      }
      private void sendAllExceptUDPPacket(Packet p,Connection con){
    	  DPacket pk = new DPacket();
    	  pk.data=p.getData();
    	  server.sendToAllExceptUDP(con.getID(), pk);
      }
}
