package me.minutz.trv.eng.etc;

public enum ObjT {
	Buton("Butonas"),
	FEREASTRA("Fer"),
	Dreptunghi("Dreptunghi");
	
	String t;
	ObjT(String s){
		t=s;
	}
	public String getStr(){
		return t;
	}
}
