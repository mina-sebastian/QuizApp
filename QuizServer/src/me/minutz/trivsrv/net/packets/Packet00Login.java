package me.minutz.trivsrv.net.packets;

public class Packet00Login extends Packet {

    private String mac="423545",username="42542525",pass="42542525";

    public Packet00Login(byte[] data) {
        super(00);
        try{
        if(data!=null){
        	if(readData(data).contains(",")){
        String[] dataArray = readData(data).split(",");
        if(dataArray.length==3){
        this.mac = dataArray[0];
        this.username = dataArray[1];
        this.pass = dataArray[2];
        		}
        	}
        }
        }catch(Exception e){
        	System.out.println(e.getCause());
        }
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
