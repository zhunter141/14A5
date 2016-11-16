package client;

import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import common.ControllerInterface;
import common.ModelInterface;
import common.ViewInterface;
import cs414.a5.Controller;
import server.ControllerImpl;

public class GameClient {
	public static void main(String args[]){
		ViewInterface view;
		ModelInterface model;
		
		ControllerInterface controller;
		
		try {
			view = new ViewImpl();
			
			controller= new ControllerImpl();
			// Let a view receive incoming calls using port 0
			//UnicastRemoteObject.exportObject(view, 0);
			
			model = (ModelInterface) Naming.lookup("rmi://localhost:2501/model");
			System.out.println("I have a model.");
			
			
			view.addModel(model);
			model.addView(view);
			view.setUpGUI();
			
			controller.addModel(model);
			controller.addView(view);
			
		} catch (MalformedURLException murle) {
			System.out.println("MalformedURLException");
			System.out.println(murle);
			System.exit(-1);
		} catch (RemoteException re) {
			System.out.println("RemoteException"); 
			System.out.println(re);
			System.exit(-1);
		} catch (NotBoundException nbe) {
			System.out.println("NotBoundException");
			System.out.println(nbe);
			System.exit(-1);
		}
	}
}
