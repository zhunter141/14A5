package server;

import java.rmi.Naming;
import common.ModelInterface;

public class GameServer {
	
	public GameServer(String numPlayer){
		try{
			ModelInterface model = new ModelImpl();
			int expectedNumPlayer = Integer.parseInt(numPlayer);
			model.setExpectedPlayer(expectedNumPlayer);
			// Binding
			Naming.rebind("rmi://localhost:2501/model",model);
			System.out.println("Game server running...");
		}catch(Exception e){
			System.out.println("Trouble: " + e );
			System.exit(-1);
		}
	}
	
	public static void main(String args[]) {
		new GameServer(args[1]);
		System.out.println(args[1]);

		
	}
}