/*
 * File: GameServerListener
 * Written By: Victor Fuentes Sangabriel
 */
package cs414.a5.distro;

import java.io.*;
import java.net.*;

import javax.swing.JFrame;

import cs414.a5.Controller;
import cs414.a5.Model;
import cs414.a5.View;

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
			//while(true){
				Controller ctrl = new Controller();
				Model model = new Model();
				View view = new View();
	
				view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				view.setLocationByPlatform(true);
				
				// link everything
				view.addModel(model);
				view.addController(ctrl);
				ctrl.addModel(model);
				ctrl.addView(view);
				model.addView(view);
	
				// initialize view
				//view.setUpGUI();
				//view.setVisible(true);
				new PlayerThread(tcpServerSocket.accept(),count,model,view,ctrl).start();
				count++;
				new PlayerThread(tcpServerSocket.accept(),count,model,view,ctrl).start();
			//}
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
