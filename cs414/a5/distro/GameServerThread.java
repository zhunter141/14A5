package cs414.a5.distro;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameServerThread extends Thread{
	private Socket tcpSocket = null;
	
	public GameServerThread(Socket tcpSocket){
		this.tcpSocket = tcpSocket;
	}
	
	public void run(){
	    Date today = new Date();
	    msg("Connection to client established.");
	    msg("Today " + today);
	    ObjectOutputStream outToClient = null;
		ObjectInputStream inFromClient = null;
	    
	    try{
	    	outToClient = new ObjectOutputStream(tcpSocket.getOutputStream());
	    	inFromClient = new ObjectInputStream(tcpSocket.getInputStream());
	    	
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
