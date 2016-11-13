package common;

import java.util.HashSet;

import cs414.a5.Board;
import cs414.a5.Player;
import cs414.a5.Square;
import cs414.a5.Token;
import server.ViewImpl;

public interface ModelInterface extends java.rmi.Remote{
	public void addView(ViewImpl v)throws java.rmi.RemoteException;
	public Player getCurrPlayer() throws java.rmi.RemoteException;
	public int getNumPlayer() throws java.rmi.RemoteException;
	public void startGame() throws java.rmi.RemoteException;
	public void rollDice() throws java.rmi.RemoteException;
	public void goToJail() throws java.rmi.RemoteException;
	public void buildHouse(Square s) throws java.rmi.RemoteException;
	public void buildHotel(Square s) throws java.rmi.RemoteException;
	public void endTurn() throws java.rmi.RemoteException;
	public void addPlayer(String name) throws java.rmi.RemoteException;
	public void sellDeed(Square d) throws java.rmi.RemoteException;
	public void buyDeed() throws java.rmi.RemoteException;
	public String EndTheGame() throws java.rmi.RemoteException;
	public void mortgage(Square s) throws java.rmi.RemoteException;
	public void umMortgage(Square s) throws java.rmi.RemoteException;
	public void auction(Object o,int[] bids) throws java.rmi.RemoteException;	  
	public Player[] getPlayers() throws java.rmi.RemoteException;
	public Token[] getTokens() throws java.rmi.RemoteException;
	public HashSet<Square> getDeeds() throws java.rmi.RemoteException;
	public Board getBoard() throws java.rmi.RemoteException;
	public String getMsg() throws java.rmi.RemoteException;
	public String endGame() throws java.rmi.RemoteException;
}