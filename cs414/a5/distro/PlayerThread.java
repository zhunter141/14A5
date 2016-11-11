package cs414.a5.distro;

import java.io.*;
import java.net.*;
import cs414.a5.*;

public class PlayerThread extends Thread{
	private Socket tcpSocket = null;
	private int playerNum;
	private Model model;
	private View view;
	private Controller ctrl;
	private boolean ready;
	
	public PlayerThread(Socket tcpSocket,int playerNum){
		System.out.println("I'm a new Player thread!");
		this.tcpSocket = tcpSocket;
		this.playerNum = playerNum;
		ready = false;
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
	    	
	    	String playerName = (String) inFromClient.readObject();
	    	msg("This is the player I got: "+playerName);
	    	
	    	// Add this player to the model
	    	model.addPlayer(playerName);
	    	
	    	System.out.println("This is the player in model: "+model.getPlayers()[playerNum-1].getName());
	    	ready = true;
	    	
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
	
	public boolean isReady(){
		return ready;
	}
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public void setView(View view){
		this.view = view;		
	}
	
	public void setController(Controller ctrl){
		this.ctrl = ctrl;		
	}
}
