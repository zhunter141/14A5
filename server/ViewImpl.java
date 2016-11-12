package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.ViewInterface;
import cs414.a5.*;

public class ViewImpl extends UnicastRemoteObject implements ViewInterface {
	private View v;
	
	public ViewImpl() throws RemoteException {
		super();
		v = new View();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void setUpGUI() throws RemoteException {
		System.out.println("Setting up GUI.");
		v.setUpGUI();
	}
}