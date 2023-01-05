package me.minutz.trivsrv.net.packets;

public class Packet05Polo extends Packet{

    private String polo;

    public Packet05Polo(byte[] data) {
        super(05);
        this.polo = readData(data);
    }

    public Packet05Polo(String polo) {
        super(05);
        this.polo = polo;
    }

    @Override
    public byte[] getData() {
        return ("05" + this.polo).getBytes();
    }

    public String getPolo() {
        return polo;
    }
}
