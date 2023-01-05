package me.minutz.trivsrv.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import me.minutz.trivsrv.Maine;
import me.minutz.trivsrv.useri.Cont;

public class SiteConn implements Runnable{
	ServerSocket serverSocket;
	public SiteConn(){
        final int portNumber = 20222;
        try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
        OutputStream os = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
        PrintWriter pw = new PrintWriter(os, true);

        BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        String line;
        try {
			while ((line = br.readLine()) != null) {
			    pw.println(getResp(line));
			}
		} catch (IOException e) {
		}
	}
	
	public String getResp(String command){
		String cmd = command.split(":")[0];
		String[] args = new String[command.split(":").length-1];
		for(int i=1;i<command.split(":").length;i++){
			args[i-1]=command.split(":")[i];
		}
		
		if(cmd.equals("getConturi")){
			String rasp = Maine.conturi.get(0).getUUID();
			for(int i=1;i<Maine.conturi.size();i++){
				rasp=rasp+":"+Maine.conturi.get(i).getUUID();
			}
			return rasp;
		}
		if(cmd.equals("getCont")){
			Cont cont = null;
			for(Cont c:Maine.conturi){
				if(c.getUUID().equals(args[0])){
					cont=c;
				}
			}
			return cont.getNume()+":"+cont.getStatus().getScut().getProcent()+":"+cont.getStatus().getResurse().getProcent()+":"+cont.getStatus().getOameni().getProcent();
		}
		return "Erroare";
	}

}
