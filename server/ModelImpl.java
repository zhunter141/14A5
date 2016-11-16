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

@SuppressWarnings("serial")
public class ModelImpl extends UnicastRemoteObject implements ModelInterface{
	private ArrayList<ViewInterface> observers = new ArrayList<ViewInterface>();
	private Board board;
	private Player[] players;
	private int expectedPlayer;
	private Bank monopolyBank;
	private Dice dice;
	private int counter;
	private int iterator;
	private Player currPlayer;
	private String msg;
	private Token[] allTokens;
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
	public Player[] getPlayers(){
		 return players;
	 }
	@Override
	public void startGame(){
		// Start the game by setting the current player
		msg = "Welcome to Monopoly Game!\n";
		msg += "Turn: ";
		currPlayer = players[0];
		msg += currPlayer.getName()+", Location: " + currPlayer.getToken().getLoc().getName()+'\n';
		msg += "Account: $"+monopolyBank.getBalance(currPlayer)+'\n';
		//view.update();
	}
	@Override
	public void addPlayer(String name){
		// Add player to game
		
		System.out.println("cs414.a5.Model: Adding: "+name+" to the game.");
		Player p = new Player(counter,name,allTokens[counter]);
		allTokens[counter].setLoc(board.getStart());
		players[counter] = p;
		counter++;
		monopolyBank.addClient(p);
		int currentPlayer= this.getPlayers().length;
		if(currentPlayer == expectedPlayer){
			this.startGame();
		}
	}
	public void setExpectedPlayer(int num){
		this.expectedPlayer = num;
	}
	
	
}