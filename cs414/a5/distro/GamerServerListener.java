/*
 * File: GameServerListener
 * Written By: Victor Fuentes Sangabriel
 */
package cs414.a5.distro;

import java.io.*;
import java.net.*;

public class GamerServerListener {
	public static void main(String args[]) throws IOException{
		ServerSocket tcpServerSocket = null;
		boolean listening = true;
		int port = 5678;
		int count = 0; // max of two players for now
		
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
			while(listening){
				new GameServerThread(tcpServerSocket.accept()).start();
				count++;
				if(count==2){
					break;
				}
			}
		}catch(IOException e){
			System.err.println("Accept failed.");
			tcpServerSocket.close();
			System.exit(1);
		}
		
		// Server okay to close now
		tcpServerSocket.close();
	}
	
	public static void msg(String s){
		System.out.println(s);
	}
}
