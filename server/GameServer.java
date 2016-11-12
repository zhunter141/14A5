package server;

import java.rmi.Naming;
import common.ViewInterface;
import cs414.a5.Controller;
import cs414.a5.Model;

public class GameServer {
	private String url;
	public GameServer(String url){
		this.url = url;
		try{
			ViewInterface view = new ViewImpl();
			Model model = new Model();
			Controller ctrl = new Controller();
			
			// link everything
			view.addModel(model);
			view.addController(ctrl);
			ctrl.addModel(model);
			ctrl.addView(view.getView());
			model.addView(view.getView());
			
			// Binding
			Naming.bind(this.url, view);
			
			System.out.println("Game server running...");
		}catch(Exception e){
			System.out.println("Trouble: " + e );
		}
	}
	
	public static void main(String args[]) {
		String url = new String("rmi://localhost:2500/GameServer");
		new GameServer(url);
	}
}
