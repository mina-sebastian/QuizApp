package me.minutz.trv.net.packets;

public class Packet00Login extends Packet {

    private String mac,username,pass;

    public Packet00Login(byte[] data) {
        super(00);
        String[] dataArray = readData(data).split(",");
        this.mac = dataArray[0];
        this.username = dataArray[1];
        this.pass = dataArray[2];
    }

    public Packet00Login(String mac, String username, String pass) {
		super(00);
		this.mac = mac;
		this.username = username;
		this.pass = pass;
    }

    @Override
    public byte[] getData() {
        return ("00" + this.mac + "," + this.username + "," + this.pass).getBytes();
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
}
