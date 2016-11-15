package common;

public interface ModelInterface extends java.rmi.Remote{
	public void addView(ViewInterface v)throws java.rmi.RemoteException;
	public void notifyAllObservers() throws java.rmi.RemoteException;
	public void rollDice() throws java.rmi.RemoteException;
}