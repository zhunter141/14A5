package common;

import java.rmi.RemoteException;

import cs414.a5.Model;
import cs414.a5.View;

public interface ModelInterface extends java.rmi.Remote{
	public void addView(View v) throws RemoteException;
	public Model getModel() throws RemoteException;
}
