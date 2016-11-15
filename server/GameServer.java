package server;

import java.rmi.Naming;
import common.ModelInterface;
import common.ViewInterface;

public class GameServer {
	
	public GameServer(){
		try{
			//ViewInterface view = new ViewImpl();
			ModelInterface model = new ModelImpl();
			//Controller ctrl = new Controller();
			
			// Binding
			//Naming.rebind("rmi://localhost:2500/view", view);
			Naming.rebind("rmi://localhost:2500/model",model);
		
			System.out.println("Game server running...");
		}catch(Exception e){
			System.out.println("Trouble: " + e );
		}
	}
	
	public static void main(String args[]) {
		new GameServer();
		System.out.println("number of player = "+ args[1]);//client takes two argument port # and number

	}
}
