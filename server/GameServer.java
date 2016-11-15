package server;

import java.rmi.Naming;

import common.ModelInterface;

public class GameServer {
	
	public GameServer(){
		try{
			ModelInterface model = new ModelImpl();
			//Controller ctrl = new Controller();
			
			// Binding
			Naming.rebind("rmi://localhost:2500/model",model);
		
			System.out.println("Game server running...");
		}catch(Exception e){
			System.out.println("Trouble: " + e );
		}
	}
	
	public static void main(String args[]) {
		new GameServer();
	}
}
