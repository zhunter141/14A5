package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.ModelInterface;
import cs414.a5.Model;
import cs414.a5.View;

public class ModelImpl extends UnicastRemoteObject implements ModelInterface{
	private Model model;
	
	public ModelImpl() throws RemoteException{
		super();
		model = new Model();
	}
	
	@Override
	public void addView(View v) throws RemoteException {
		model.addView(v);
	}
	
	@Override
	public Model getModel() throws RemoteException{
		return model;
	}

}
