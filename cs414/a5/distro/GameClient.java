package cs414.a5.distro;

import java.io.*;
import java.net.*;
import cs414.a5.*;

public class GameClient {
	public static void main(String args[]) throws IOException, ClassNotFoundException{
		Socket tcpSocket = null;
		String ipAdder = "localhost";// will need to be changed to remote server
		InetAddress addr = null;
		ObjectOutputStream outToServer = null;
		ObjectInputStream inFromServer = null;
		
		try{
			addr = InetAddress.getByName(ipAdder);
			tcpSocket = new Socket(addr,5678);
			outToServer = new ObjectOutputStream(tcpSocket.getOutputStream());
	    	inFromServer = new ObjectInputStream(tcpSocket.getInputStream());
	    	
	    	//We need to get the Welcome screen so the player can enter their name
	    	WelcomeScreen init = (WelcomeScreen) inFromServer.readObject();
	    	
		}catch(UnknownHostException e){
			System.err.println("Unknown host: "+ addr);
			System.exit(-1);
		}
		catch(IOException e){
			System.err.println("Could not find IO for host: "+ addr);
			System.exit(-1);
		}
		
		// close everything
		tcpSocket.close();
		outToServer.close();
		inFromServer.close();
	}
}
