package me.minutz.trv.net;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import me.minutz.trv.MainMinutz;
import me.minutz.trv.Meniu;
import me.minutz.trv.eng.Game;
import me.minutz.trv.eng.etc.HWindow;
import me.minutz.trv.net.Network.DPacket;
import me.minutz.trv.net.packets.Packet;
import me.minutz.trv.net.packets.Packet.PacketTypes;
import me.minutz.trv.net.packets.Packet00Login;
import me.minutz.trv.net.packets.Packet01Disconnect;
import me.minutz.trv.net.packets.Packet03Kick;
import me.minutz.trv.net.packets.Packet04Check;
import me.minutz.trv.net.packets.Packet05Polo;
import me.minutz.trv.net.packets.Packet06Set;

public class KClient {
	
	public Client client;
	public int id;
	Meniu m;
	
	public KClient(){
		m = new Meniu();
		Log.DEBUG=false;
	    client = new Client();
	    client.start();
	    Network.register(client);
	    try {
			client.connect(5000, "localhost", 56643, 56644);
		} catch (IOException e) {
			System.out.println(e.getCause());
			System.exit(1);
		}
	    id=client.getID();
	    client.addListener(new Listener() {
	        public void received (Connection connection, Object object) {
	           if (object instanceof DPacket) {
	              DPacket response = (DPacket)object;
	              parsePacket(response.data,connection);
	           }
	        }
	     });
	    client.addListener(new Listener(){
	    	public void disconnected(Connection connection){
	    		if(connection.getID()==client.getID()){
	    			System.exit(1);
	    		}
	    	}
	    });
	    Packet k = new Packet04Check(MainMinutz.getMAC());
	    sendPacket(k);
	}
	
	public void sendPacket(Packet k){
	    DPacket p = new DPacket();
	    p.data=k.getData();
	    client.sendTCP(p);
	}
	
    private void parsePacket(byte[] data,Connection conn) {
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
        default:
        case INVALID:
        	break;
        case KICK:
        	packet = new Packet03Kick(data);
        	if(MainMinutz.hw!=null){
        	MainMinutz.hw.frame.setVisible(false);
        	}
        	if(Meniu.preFrame!=null){
        	Meniu.preFrame.setVisible(false);
        	}
        	System.out.println(((Packet03Kick) packet).getReason());
        	JOptionPane.showMessageDialog(new JFrame(), ((Packet03Kick) packet).getReason(),"INFO",JOptionPane.INFORMATION_MESSAGE);	        
        	System.exit(1);
        	break;
        case DISCONNECT:
            packet = new Packet01Disconnect(data);
            System.out.println("[" + conn.getRemoteAddressTCP().getAddress().getHostAddress() + ":" + conn.getRemoteAddressTCP().getPort() + "] "
                    + ((Packet01Disconnect) packet).getUsername() + " has left...");
            break;
        case LOGIN:
        	packet = new Packet00Login(data);
        	MainMinutz.uname=((Packet00Login) packet).getUsername();
        	MainMinutz.hw=new HWindow(1340 ,680 ,MainMinutz.uname ,new Game());
        	if(Meniu.preFrame!=null){
        	Meniu.preFrame.setVisible(false);
        	Meniu.preFrame=null;
        	}
        	break;
        case POLO:
        	packet = new Packet05Polo(data);
        	String polo = ((Packet05Polo)packet).getPolo();
        	if(polo.equals("nuexista")){
        		m.log();
        	}else{
        		if(polo.startsWith("martzafoi")){
        		if(polo.contains(":")){
        			if(polo.split(":").length==2){
        				String cmd = polo.split(":")[0].replace("passcode", "").replace("/2434", ":");
        				String args = polo.split(":")[1].replace("/2434", ":");
        			}else{
                		JOptionPane.showMessageDialog(new JFrame(), ((Packet05Polo) packet).getPolo(),"INFO",JOptionPane.INFORMATION_MESSAGE);
        			}
        		}else{
        		JOptionPane.showMessageDialog(new JFrame(), ((Packet05Polo) packet).getPolo(),"INFO",JOptionPane.INFORMATION_MESSAGE);
        		}
        		}else{
            		JOptionPane.showMessageDialog(new JFrame(), ((Packet05Polo) packet).getPolo(),"INFO",JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        case SET:
        	packet = new Packet06Set(data);
        	MainMinutz.scut=((Packet06Set)packet).getS();
        	MainMinutz.res=((Packet06Set)packet).getR();
        	MainMinutz.oam=((Packet06Set)packet).getO();
        }
    }
}
