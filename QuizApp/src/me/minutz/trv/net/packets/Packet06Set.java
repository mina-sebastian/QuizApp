package me.minutz.trv.net.packets;

public class Packet06Set extends Packet{

    private int r,s,o;

    public Packet06Set(byte[] data) {
        super(06);
        String[] d = readData(data).split(",");
        try{
        	r=Integer.parseInt(d[0]);
        	s=Integer.parseInt(d[1]);
        	o=Integer.parseInt(d[2]);
        }catch(Exception e){
        	System.out.println(e.getCause());
        }
    }

    public Packet06Set(int r, int s,int o) {
        super(06);
        this.r=r;
        this.s=s;
        this.o=o;
    }

    @Override
    public byte[] getData() {
        return ("06" + ""+r+","+s+","+o).getBytes();
    }

	public int getR() {
		return r;
	}

	public int getS() {
		return s;
	}

	public int getO() {
		return o;
	}
    
}
