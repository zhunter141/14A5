package server;

import java.rmi.Naming;
import common.ModelInterface;
import common.ViewInterface;

public class GameServer {
	private String url;
	public GameServer(String url){
		this.url = url;
		try{
			ViewInterface view = new ViewImpl();
			ModelInterface model = new ModelImpl();
			//Controller ctrl = new Controller();
			
			// link everything
			view.addModel(model.getModel());
			//view.addController(ctrl);
			//ctrl.addModel(model);
			//ctrl.addView(view);
			model.addView(view.getView());
			
			// Binding
			Naming.rebind(this.url, view);
			Naming.rebind("rmi://localhost:2500/model",model);
		
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
