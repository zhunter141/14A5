package cs414.a5.distro;

import java.io.*;
import java.net.*;

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
	    	
	    	// Send the GameClient its player number
	    	outToClient.writeObject(playerNum);
	    	
	    	//String playerName = (String) inFromClient.readObject();
	    	//msg("This is the player I got: "+playerName);
	    	
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
