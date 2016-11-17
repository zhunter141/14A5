package server;

import java.rmi.Naming;
import common.ModelInterface;

public class GameServer {
	
	public GameServer(String numPlayer){
		try{
			System.setProperty("java.rmi.server.hostname","localhost");// Fixes Connection refused on my machine - vf
			int expectedNumPlayer = Integer.parseInt(numPlayer);
			
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