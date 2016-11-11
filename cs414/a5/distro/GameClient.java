package cs414.a5.distro;

import java.io.*;
import java.net.*;

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
	    	
	    	// Get the player num from the player thread
	    	int playerNum = (Integer) inFromServer.readObject();
	    	
	    	System.out.println("This is the player num I have: "+playerNum);
	    	
	    	//We need to get the player name
	    	PlayerDummy player = new PlayerDummy();
	    	WelcomeScreenController myController = new WelcomeScreenController();
	    	WelcomeScreen myScreen = new WelcomeScreen(playerNum,player);
	    	
	    	// set up ctrl and screen ref
	    	myController.setScreen(myScreen);
			myScreen.setController(myController);
	    	myScreen.setupGUI();
	    	myScreen.setVisible(false);
	    	myScreen.setVisible(true);
	    	while(myScreen.getName().compareTo("")==0){
	    		//System.out.println("Player name has not been entered.");
	    		System.out.println("");
	    	}
	    	System.out.println("From GC this is the name to give the PlayerThread: "+player.getName());
	    	outToServer.writeObject(player.getName());
		}catch(UnknownHostException e){
			System.err.println("Unknown host: "+ addr);
			System.exit(-1);
		}
		catch(IOException e){
			System.err.println("Could not find IO for host: "+ addr);
			System.exit(-1);
		}
		
		//close everything 
    	tcpSocket.close();
    	outToServer.close();
    	inFromServer.close();
    	System.out.println("here :(");
	}
}
