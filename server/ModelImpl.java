package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.ModelInterface;
import common.ViewInterface;
import cs414.a5.Bank;
import cs414.a5.Board;
import cs414.a5.Dice;
import cs414.a5.Player;
import cs414.a5.Token;
import cs414.a5.View;

@SuppressWarnings("serial")
public class ModelImpl extends UnicastRemoteObject implements ModelInterface{
	private ArrayList<ViewInterface> observers = new ArrayList<ViewInterface>();
	private Board board;
	private Player[] players;
	private Token[] allTokens;
	private Bank monopolyBank;
	private int counter;
	
	public ModelImpl() throws RemoteException {
		super();	
		board = new Board();
		board.initialize();
	}
	
	@Override
	public void addView(ViewInterface v) throws RemoteException {
		observers.add(v);
	}

	@Override
	public void notifyAllObservers() throws RemoteException {
		for(ViewInterface v : observers){
			v.update();
		}
	}
	
	@Override
	public void rollDice() throws RemoteException{
		System.out.println("From model, player has rolled dice.");
		notifyAllObservers();
	}

	@Override
	public Board getBoard() throws RemoteException {
		return this.board;
	}

	@Override
	public void addPlayer(String playerName) throws RemoteException{
		System.out.println("Adding "+playerName+" to game!");
		Player p = new Player(counter,name,allTokens[counter]);
		allTokens[counter].setLoc(board.getStart());
		players[counter] = p;
		counter++;
		monopolyBank.addClient(p);
	}
}