package common;

import cs414.a5.Board;
import cs414.a5.Player;

public interface ModelInterface extends java.rmi.Remote{
	public void addView(ViewInterface v)throws java.rmi.RemoteException;
	public void notifyAllObservers() throws java.rmi.RemoteException;
	public void rollDice() throws java.rmi.RemoteException;
	public Board getBoard() throws java.rmi.RemoteException;
	public Player[] getPlayers() throws java.rmi.RemoteException;
	public void addPlayer(String name) throws java.rmi.RemoteException;
	public void setExpectedPlayer(int num) throws java.rmi.RemoteException;
	public void startGame() throws java.rmi.RemoteException;
}