package common;

import cs414.a5.Board;

public interface ModelInterface extends java.rmi.Remote{
	public void addView(ViewInterface v)throws java.rmi.RemoteException;
	public void notifyAllObservers() throws java.rmi.RemoteException;
	public void rollDice() throws java.rmi.RemoteException;
	public Board getBoard() throws java.rmi.RemoteException;
	public void addPlayer(String playerName) throws java.rmi.RemoteException;
}