package me.minutz.trv.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import me.minutz.trv.MainMinutz;
import me.minutz.trv.net.packets.Packet;
import me.minutz.trv.net.packets.Packet.PacketTypes;
import me.minutz.trv.net.packets.Packet00Login;
import me.minutz.trv.net.packets.Packet01Disconnect;

public class GameClient implements Runnable {

    private InetAddress ipAddress;
    private DatagramSocket socket;

    public GameClient(String ipAddress) {
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
            Packet00Login p = new Packet00Login(MainMinutz.getMAC(), "--------", "-----------");
            System.out.println("se trimite");
            sendPacket(p);
            System.out.println("gata");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
        default:
        case INVALID:
            break;
        case DISCONNECT:
            packet = new Packet01Disconnect(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + ((Packet01Disconnect) packet).getUsername() + " has left...");
        }
    }

    public void sendPacket(Packet p){
    	sendData(p.getData());
    }
    
    private void sendData(byte[] data) {
            DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1500);
            try {
                socket.send(packet);
                socket.setSoTimeout(1000);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
