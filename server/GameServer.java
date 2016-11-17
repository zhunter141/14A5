package server;

import java.rmi.Naming;

import common.ControllerInterface;
import common.ModelInterface;

public class GameServer {
	
	public GameServer(String numPlayer){
		try{
			
 			ControllerInterface controller = new ControllerImpl(); // by hj 

			int expectedNumPlayer = Integer.parseInt(numPlayer);
			System.out.println("Expected number of players = "+expectedNumPlayer);
			ModelInterface model = new ModelImpl(expectedNumPlayer);
			model.setExpectedPlayer(expectedNumPlayer);

			// Binding
			Naming.rebind("rmi://localhost:2500/model",model);
			System.out.println("Game server running...");
		}catch(Exception e){
			System.out.println("Trouble: " + e );
			System.exit(-1);
		}
	}
	
	public static void main(String args[]) {
		new GameServer(args[0]);
	}
}