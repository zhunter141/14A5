package cs414.a5.distro;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class PlayerThread extends Thread{
	private Socket tcpSocket = null;
	private int playerNum;
	
	public PlayerThread(Socket tcpSocket,int playerNum){
		this.tcpSocket = tcpSocket;
		this.playerNum = playerNum;
	}
	
	public void run(){
	    //Date today = new Date();
	    //msg("Connection to client established.");
	    //msg("Today " + today);
	    ObjectOutputStream outToClient = null;
		ObjectInputStream inFromClient = null;
	    
	    try{
	    	outToClient = new ObjectOutputStream(tcpSocket.getOutputStream());
	    	inFromClient = new ObjectInputStream(tcpSocket.getInputStream());
	    	
	    	// Need to send the welcome screen to the player
	    	outToClient.writeObject(new WelcomeScreen(playerNum));
	    	// Close everything
		    outToClient.close();
		    inFromClient.close();
		    
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	}
	
	private void msg(String s){
		System.out.println(s);
	}
}
