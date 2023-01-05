package me.minutz.trivsrv.net.packets;

public class Packet04Check extends Packet{

    private String mac;

    public Packet04Check(byte[] data) {
        super(04);
        this.mac = readData(data);
    }

    public Packet04Check(String mac) {
        super(04);
        this.mac = mac;
    }

    @Override
    public byte[] getData() {
        return ("04" + this.mac).getBytes();
    }

    public String getMac() {
        return mac;
    }
}
