package me.minutz.trivsrv.net.packets;

public class Packet03Kick extends Packet {

    private String reason;

    public Packet03Kick(byte[] data) {
        super(03);
        this.reason = readData(data);
    }

    public Packet03Kick(String reason) {
        super(03);
        this.reason = reason;
    }

    @Override
    public byte[] getData() {
        return ("03" + this.reason).getBytes();
    }

    public String getReason() {
        return reason;
    }

}
