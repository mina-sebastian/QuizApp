package me.minutz.trv.net.packets;

public class Packet02Register extends Packet {

    private String mac,username,pass,pass2;

    public Packet02Register(byte[] data) {
        super(02);
        String[] dataArray = readData(data).split(",");
        this.mac = dataArray[0];
        this.username = dataArray[1];
        this.pass = dataArray[2];
        this.pass2 = dataArray[3];
    }

    public Packet02Register(String mac, String username, String pass, String pass2) {
		super(02);
		this.mac = mac;
		this.username = username;
		this.pass = pass;
		this.pass2 = pass2;
    }

    @Override
    public byte[] getData() {
        return ("02" + this.mac + "," + this.username + "," + this.pass + "," + this.pass2).getBytes();
    }

    public String getUsername() {
        return username;
    }

	public String getMac() {
		return mac;
	}

	public String getPass() {
		return pass;
	}
	
	public String getPass2() {
		return pass2;
	}
}
