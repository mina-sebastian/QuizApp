package me.minutz.trivsrv.joc.vars.intreb;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import me.minutz.trivsrv.comenzi.Consola;
import me.minutz.trivsrv.config.Config;
import me.minutz.trivsrv.config.IntrebConfig;

public class Dialog extends JDialog implements KeyListener{
	
	public JTextField jtf;
	public JCheckBox a,b;
	private int i = 0;
	private Intrebare intr;
	
	public Dialog(Intrebare in,int i){
		this.i=i;
		this.intr=in;
		setSize(300, 60);
		if(i==0){
			setSize(300, 70);
		setTitle("Selecteaza tipul");
		}else{
			if(i==1){
				setTitle("Adauga o intrebare");
			}else{
				if(i==2){
					setTitle("Adauga o intrebare");
				}else{
					if(i==3){
						setTitle("Adauga raspunsul");
					}else{
						if(i==4){
							setTitle("Adauga raspunsul");
						}else{
							if(i==5){
								setTitle("Scrie categoria");
							}else{
								if(i==6){
									setTitle("Adauga varianta 2");
								}else{
									if(i==7){
										setTitle("Adauga varianta 3");
									}else{
										if(i==8){
											setTitle("Adauga varianta 4");
										}else{
											if(i==9){
												setTitle("Scrie categoria");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		setResizable(false);
		if(i==0){
		a=new JCheckBox("Tipul A");
		a.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				intr = new AIntrebare(null, null, null);
				new Dialog(intr,1);
			}
		});
		b=new JCheckBox("Tipul B");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				intr = new BIntrebare(null, null, null, null, null, null);
				new Dialog(intr,2);
			}
			
		});
		add(BorderLayout.WEST,a);
		add(BorderLayout.EAST,b);
		requestFocusInWindow();
	}else{
		jtf=new JTextField(20);
		jtf.addKeyListener(this);
		add(jtf);
		jtf.requestFocusInWindow();
	}
		setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent k) {
		if(isVisible()){
		if(k.getKeyCode()==KeyEvent.VK_ENTER){
				setVisible(false);
				//A INTREBARE
				if(i==1){
					((AIntrebare)intr).setIntrebare(jtf.getText());
					new Dialog(intr,3);
				}
				if(i==3){
					((AIntrebare)intr).setRaspuns(jtf.getText());
					new Dialog(intr,5);
				}
				if(i==5){
					Categ c = Categ.getByString(jtf.getText());
					((AIntrebare)intr).setCateg(c);
					IntrebConfig.addIntr(intr);
					Consola.display("Intrebare adaugata!");
				}
				
				//B INTREBARE
				if(i==2){
					((BIntrebare)intr).setIntrebare(jtf.getText());
					new Dialog(intr,4);
				}
				if(i==4){
					((BIntrebare)intr).setRaspuns(jtf.getText());
					new Dialog(intr,6);
				}
				if(i==6){
					((BIntrebare)intr).setVar2(jtf.getText());
					new Dialog(intr,7);
				}
				if(i==7){
					((BIntrebare)intr).setVar3(jtf.getText());
					new Dialog(intr,8);
				}
				if(i==8){
					((BIntrebare)intr).setVar4(jtf.getText());
					new Dialog(intr,9);
				}
				if(i==9){
					Categ c = Categ.getByString(jtf.getText());
					((BIntrebare)intr).setCateg(c);
					IntrebConfig.addIntr(intr);
					Consola.display("Intrebare adaugata!");
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
