package me.minutz.trivsrv.joc.vars.intreb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BIntrebare extends Intrebare{
	String var2,var3,var4;
	public BIntrebare(String intrebare, String raspuns,String va2, String va3, String va4, Categ categ) {
		super(intrebare, raspuns, ITip.u, categ);
		var2=va2;
		var3=va3;
		var4=va4;
	}
	public String getVar2() {
		return var2;
	}
	public String getVar3() {
		return var3;
	}
	public String getVar4() {
		return var4;
	}
	
	public void setVar2(String var2) {
		this.var2 = var2;
	}
	public void setVar3(String var3) {
		this.var3 = var3;
	}
	public void setVar4(String var4) {
		this.var4 = var4;
	}
	public List<String> getVariante(){
		List<String> l = new ArrayList<String>();
		l.add(var2);
		l.add(var4);
		l.add(getRaspuns());
		l.add(var3);
		long seed = System.nanoTime();
		Collections.shuffle(l, new Random(seed));
		return l;
	}
}
