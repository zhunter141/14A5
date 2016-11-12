package common;

import cs414.a5.Controller;
import cs414.a5.Model;
import cs414.a5.View;

public interface ViewInterface extends java.rmi.Remote{
	public void setUpGUI() throws java.rmi.RemoteException;
	public void addModel(Model m) throws java.rmi.RemoteException;
	public void addController(Controller c) throws java.rmi.RemoteException;
	public View getView() throws java.rmi.RemoteException;
}