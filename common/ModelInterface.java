package common;

import java.rmi.RemoteException;

import cs414.a5.Board;
import cs414.a5.Square;
import cs414.a5.Player;

public interface ModelInterface extends java.rmi.Remote{
	public void addView(ViewInterface v)throws java.rmi.RemoteException;
	public void rollDice() throws java.rmi.RemoteException;
	public Board getBoard() throws java.rmi.RemoteException;
	public Player[] getPlayers() throws java.rmi.RemoteException;
	public void setExpectedPlayer(int num) throws java.rmi.RemoteException;
	public void addPlayer(String playerName) throws java.rmi.RemoteException;
	public void startGame() throws java.rmi.RemoteException;
	
	//HJ : Only for non error to check with Professor
	public void buyDeed() throws java.rmi.RemoteException;
	public void endTurn() throws java.rmi.RemoteException;
	public Object getDeeds() throws java.rmi.RemoteException;
	public int getNumPlayer() throws java.rmi.RemoteException;
	public void auction(Object o,int[] bits) throws java.rmi.RemoteException;
	public Object endGame() throws java.rmi.RemoteException;
	public void sellDeed(Square myDeed) throws java.rmi.RemoteException;
	public void buildHouse(Square myDeed) throws java.rmi.RemoteException;
	public void buildHotel(Square myDeed) throws java.rmi.RemoteException;
	public void mortgage(Square myDeed) throws java.rmi.RemoteException;
	public void umMortgage(Square myDeed) throws java.rmi.RemoteException;
	public void notifyAllObserversOfBoard() throws RemoteException;
	public void notifyAllObserversOfMsg() throws RemoteException;
	public String getMsg() throws RemoteException;
	public void move(int steps) throws RemoteException;
	void startAuction(Square s) throws RemoteException;
}