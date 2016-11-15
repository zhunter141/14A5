package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.ModelInterface;
import common.ViewInterface;
import cs414.a5.Board;

@SuppressWarnings("serial")
public class ModelImpl extends UnicastRemoteObject implements ModelInterface{
	private ArrayList<ViewInterface> observers = new ArrayList<ViewInterface>();
	private Board board;
	
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
}