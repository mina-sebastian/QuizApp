package me.minutz.trivsrv.joc.vars.intreb;

public enum Categ {
	MATE("MATE"),
	BIO("BIO"),
	SPORT("SPORT"),
	MANCARE("MANCARE"),
	SOCIETATE("SOCIETATE"),
	TEATRU("TEATRU");
	
	private String al;
	Categ(String s){
		al=s;
	}
	
	public String getString(){
		return al;
	}
	public static Categ getByString(String s){
		for(Categ c:Categ.values()){
			if(c.getString().equalsIgnoreCase(s)){
				return c;
			}
		}
		return null;
	}
}
