package me.minutz.trv;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.minutz.trv.net.packets.Packet00Login;
import me.minutz.trv.net.packets.Packet02Register;

public class Meniu {
	
    String      appName     = "StArTi";
    JButton     sendMessage;
    JTextField  usernameChooser;
    JTextField  pass;
    public static JFrame preFrame;
    EnterL kutil;
    int i = 0;
    
    public void log() {
    	i=0;
        kutil=new EnterL(this);
        preFrame = new JFrame(appName);
        preFrame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(1);
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        usernameChooser = new JTextField(15);
        pass = new JPasswordField(15);
        JLabel chooseUsernameLabel = new JLabel("Username:");
        JLabel passLbl = new JLabel("Password:");
        JButton enterServer = new JButton("Login");
        enterServer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enterLog();
			}
        });
        JButton goreg = new JButton("Register");
        goreg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enterReg();
			}
        });
        JPanel prePanel = new JPanel(new GridBagLayout());

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        prePanel.add(passLbl, preLeft);
        prePanel.add(pass, preRight);
        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, goreg);
        preFrame.add(BorderLayout.EAST, enterServer);
        
        preFrame.setSize(600, 600);
        preFrame.setResizable(false);
        preFrame.setVisible(true);
        pass.addKeyListener(kutil);
        usernameChooser.addKeyListener(kutil);
    }
    
    public void enterLog(){
    	if(usernameChooser.getText()==null){
    		return;
    	}
    	if(usernameChooser.getText().replace(" ", "").isEmpty()){
    		return;
    	}
    	if(pass.getText()==null){
    		return;
    	}
    	if(pass.getText().replace(" ", "").isEmpty()){
    		return;
    	}
    	MainMinutz.client.sendPacket(new Packet00Login(MainMinutz.getMAC(),usernameChooser.getText(),pass.getText()));
    }
    public void enterReg(){
    	if(usernameChooser.getText()==null){
    		return;
    	}
    	if(usernameChooser.getText().replace(" ", "").isEmpty()){
    		return;
    	}
    	if(pass.getText()==null){
    		return;
    	}
    	if(pass.getText().replace(" ", "").isEmpty()){
    		return;
    	}
    	MainMinutz.client.sendPacket(new Packet02Register(MainMinutz.getMAC(),usernameChooser.getText(),pass.getText(),pass.getText()));
    }

}
