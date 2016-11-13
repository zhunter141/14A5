/*
 * File: GameServerListener
 * Written By: Victor Fuentes Sangabriel
 */
package cs414.a5.distro;

import java.io.*;
import java.net.*;

public class GameServerListener {
	public static void main(String args[]) throws IOException{
		ServerSocket tcpServerSocket = null;
		//boolean listening = true;
		int port = 5678;
		int count = 1; // max of two players for now
		
		// Establish TCP connection
		try{
			tcpServerSocket = new ServerSocket(port);
			msg("Initializing server...\nListening on port "+port);
		}catch(IOException e){
			System.err.println("Could not listen on port "+port);
			System.exit(1);
		}
		
		// Successful able to listen on port 5678
		// start listening for incoming connections
		try{
			Socket player1 = tcpServerSocket.accept();
			ObjectInputStream fromPlayer = new ObjectInputStream(player1.getInputStream());
			
			String name = (String) fromPlayer.readObject();
			
			System.out.println("This p1 name: "+name);
			// The first thing sent by the player should be the name
			/*
			 * 
			PlayerThread p1 = new PlayerThread(tcpServerSocket.accept(),count);
			count++;
			PlayerThread p2 =new PlayerThread(tcpServerSocket.accept(),count);
			
			// Start GameThread with current players
			GameThread gt = new GameThread(p1,p2);
			gt.start();
			 */
		}catch(IOException | ClassNotFoundException e){
			System.err.println("Accept failed.");
			tcpServerSocket.close();
			System.exit(1);
		}
		
		// Server okay to close now
		tcpServerSocket.close();
		msg("GS done!");
	}
	
	public static void msg(String s){
		System.out.println(s);
	}
}
