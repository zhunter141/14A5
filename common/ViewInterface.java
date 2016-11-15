package common;

public interface ViewInterface extends java.rmi.Remote{
	public void setUpGUI() throws java.rmi.RemoteException;
	public void addModel(ModelInterface model) throws java.rmi.RemoteException;
	public void update() throws java.rmi.RemoteException;
	public void rollDice() throws java.rmi.RemoteException;
}