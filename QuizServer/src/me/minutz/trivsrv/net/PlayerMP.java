package me.minutz.trivsrv.net;

import java.net.InetAddress;

import com.esotericsoftware.kryonet.Connection;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.joc.macaroane.Joc;
import me.minutz.trivsrv.net.Network.DPacket;
import me.minutz.trivsrv.net.packets.Packet;
import me.minutz.trivsrv.useri.Cont;

public class PlayerMP
{
  private String mac;
  private Connection conn;
  private Cont cont;
  private InetAddress tcpaddr,udpaddr;
  private int tcpport,udpport;
  private Joc joc;

  public PlayerMP(String mac,Connection conn,Cont cont)
  {
    this.mac = mac;
    this.conn = conn;
    this.cont = cont;
    
    tcpaddr=conn.getRemoteAddressTCP().getAddress();
    udpaddr=conn.getRemoteAddressUDP().getAddress();
    
    tcpport=conn.getRemoteAddressTCP().getPort();
    udpport=conn.getRemoteAddressUDP().getPort();
  }

  public String getMac() {
    return this.mac;
  }

  public Connection getConnection(){
	  return conn;
  }

  public Cont getCont() {
    return this.cont;
  }

  public void setCont(Cont cont) {
    this.cont = cont;
  }
  
  public InetAddress getIpAddress(){
	  if(Maine.tcp){
	  return tcpaddr;
	  }else{
		  return udpaddr;
	  }
  }
  
  public int getPort(){
	  if(Maine.tcp){
	  return tcpport;
	  }else{
		  return udpport;
	  }
  }
  
  public void sendPacket(Packet p){
	  if(Maine.tcp){
		  DPacket k = new DPacket();
		  k.data=p.getData();
		  conn.sendTCP(k);
	  }else{
		  DPacket k = new DPacket();
		  k.data=p.getData();
		  conn.sendUDP(k);		  
	  }
  }
  
  public Joc getJoc(){
	  return joc;
  }
  
  public void setJoc(Joc j){
	  joc=j;
  }
}