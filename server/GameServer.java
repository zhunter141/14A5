package server;

import java.rmi.Naming;
import common.View;

public class GameServer {
	private String url;
	public GameServer(String url){
		this.url = url;
		try{
			View v = new ViewImpl();
			Naming.bind(this.url, v);
			System.out.println("Game server running...");
		}catch(Exception e){
			System.out.println("Trouble: " + e );
		}
	}
	
	public static void main(String args[]) {
		String url = new String("rmi://localhost:9999/GameServer");
		new GameServer(url);
	}
}
