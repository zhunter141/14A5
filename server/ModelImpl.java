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
	private int counter = 0;
	private int iterator;
	private Player currPlayer;
	private String msg;
	private Token[] allTokens;
	private boolean hasRolled;

	
	public ModelImpl(int numPlayers) throws RemoteException {
		super();	
		board = new Board();
		board.initialize();
		allTokens = new Token[numPlayers];		
		dice = new Dice();
		monopolyBank = new Bank();
		
		//board.initCards();
		msg = "";
		players = new Player[numPlayers];
		iterator = 0;
		counter = 0;
		createTokens();
		hasRolled = false;
	}
	private void createTokens(){
		Token t1 =new Token("Horse");
		Token t2 =new Token("Car");
		//Token t3 =new Token("Mouse");
		//Token t4 =new Token("Hat");
		allTokens[0] = t1;		
		allTokens[1] = t2;
		//allTokens[2] = t3;
		//allTokens[3] = t4;
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
	public Player[] getPlayers()throws RemoteException{
		 return players;
	 }
//	@Override
//	public void startGame()throws RemoteException{
//		// Start the game by setting the current player
//		msg = "Welcome to Monopoly Game!\n";
//		msg += "Turn: ";
//		currPlayer = players[0];
//		msg += currPlayer.getName()+", Location: " + currPlayer.getToken().getLoc().getName()+'\n';
//		msg += "Account: $"+monopolyBank.getBalance(currPlayer)+'\n';
//		//view.update();
//	}
	@Override
	public void addPlayer(String name)throws RemoteException{
		// Add player to game
		System.out.println("Adding "+name+" to game!");
		System.out.println("cs414.a5.Model: Adding: "+name+" to the game.");
		Player p = new Player(counter,name,allTokens[counter]);  //nullPointer exception
		allTokens[counter].setLoc(board.getStart());
		players[counter] = p;
		counter++;
		monopolyBank.addClient(p);
		int currentPlayer= this.getPlayers().length;
//		if(currentPlayer == expectedPlayer){
//			this.startGame();
//		}
	}
	public void setExpectedPlayer(int num){
		this.expectedPlayer = num;
	}



}