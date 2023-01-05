package me.minutz.trivsrv.net.packets;

public class Packet02Register extends Packet {

    private String mac="3253232523",username="3523523",pass="34234324",pass2="32532523523";

    public Packet02Register(byte[] data) {
        super(02);
        try{
        if(data!=null){
        if(readData(data)!=null){
        String[] dataArray = readData(data).split(",");
        if(dataArray.length==4){
        this.mac = dataArray[0];
        this.username = dataArray[1];
        this.pass = dataArray[2];
        this.pass2 = dataArray[3];
        	}
          }
        }
        }catch(Exception e){
        	System.out.println(e.getCause());
        }
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
