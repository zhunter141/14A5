package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.ModelInterface;
import common.ViewInterface;

@SuppressWarnings("serial")
public class ModelImpl extends UnicastRemoteObject implements ModelInterface{
	private ArrayList<ViewInterface> observers = new ArrayList<ViewInterface>();
	
	public ModelImpl() throws RemoteException {
		super();	
	}
	
	@Override
	public void addView(ViewInterface v) throws RemoteException {
		observers.add(v);
		
	}

	@Override
	public void notifyAllObservers() throws RemoteException {
		for(ViewInterface v : observers){
			v.update();
		}
	}
}