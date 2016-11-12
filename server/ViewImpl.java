package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.View;

public class ViewImpl extends UnicastRemoteObject implements View {
	
	public ViewImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void setUpGUI() throws RemoteException {
		System.out.println("Setting up GUI.");
	}
}