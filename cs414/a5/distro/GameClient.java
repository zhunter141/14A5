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
		
		//We need to get the player name
		PlayerDummy player = new PlayerDummy();
		EnterPlayerScreenController myEnterPlayerController = new EnterPlayerScreenController();
		EnterPlayerScreen myEnterPlayerScreen = new EnterPlayerScreen(player);
		
		// set up ctrl and screen connections
		myEnterPlayerController.setScreen(myEnterPlayerScreen);
		myEnterPlayerScreen.setController(myEnterPlayerController);
		myEnterPlayerController.setPlayer(player);
		myEnterPlayerScreen.setupGUI();
		
		// toggle screen
		myEnterPlayerScreen.setVisible(false);
		myEnterPlayerScreen.setVisible(true);
		
		while(true){
			// wait here until name has been entered by player
			if(!player.hasName()){
				System.out.print("");
			}
			else{
				// player has name so break
				break;
			}
		}
		
		System.out.println("This is the player I got: "+player.getName());
		
		try{
			// socket instantiation 
			System.out.println("Finding game...");
			addr = InetAddress.getByName(ipAdder);
			tcpSocket = new Socket(addr,5678);
			
			// Set up Object Streams
			outToServer = new ObjectOutputStream(tcpSocket.getOutputStream());
	    	inFromServer = new ObjectInputStream(tcpSocket.getInputStream());
	    	
	    	// send player name to player thread
	    	outToServer.writeObject(player.getName());
	    	
	    	System.out.println("Looking for players...");
		
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
	}
}
