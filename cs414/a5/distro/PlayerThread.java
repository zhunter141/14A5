package cs414.a5.distro;

import java.io.*;
import java.net.*;
import java.sql.Date;

import javax.swing.JOptionPane;

public class PlayerThread extends Thread{
	private Socket tcpSocket = null;
	private int playerNum;
	
	public PlayerThread(Socket tcpSocket,int playerNum){
		System.out.println("I'm a new Player thread!");
		this.tcpSocket = tcpSocket;
		this.playerNum = playerNum;
	}
	
	public void run(){
	    msg("Connection to client established.");
	    ObjectOutputStream outToClient = null;
		ObjectInputStream inFromClient = null;
	    
	    try{
	    	outToClient = new ObjectOutputStream(tcpSocket.getOutputStream());
	    	inFromClient = new ObjectInputStream(tcpSocket.getInputStream());
	    	
	    	// Need to send the welcome screen to the player and the controller for it
			WelcomeScreenController myController = new WelcomeScreenController();
			WelcomeScreen myScreen = new WelcomeScreen(playerNum);
			//myController.setScreen(myScreen);
			//myScreen.setController(myController);
			
	    	outToClient.writeObject(myController);
	    	outToClient.writeObject(myScreen);
	    	
	    	String playerName = (String) inFromClient.readObject();
	    	msg("This is the player I got: "+playerName);
	    	// Close everything
		    outToClient.close();
		    inFromClient.close();
		    
	    }catch(IOException | ClassNotFoundException e){
	    	e.printStackTrace();
	    }
	}
	
	private void msg(String s){
		System.out.println(s);
	}
}
