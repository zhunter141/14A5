package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;

import common.ModelInterface;
import common.ViewInterface;
import cs414.a5.Bank;
import cs414.a5.Board;
import cs414.a5.Card;
import cs414.a5.Deed;
import cs414.a5.Dice;
import cs414.a5.Player;
import cs414.a5.RailRoad;
import cs414.a5.Square;
import cs414.a5.Token;
import cs414.a5.Utility;

@SuppressWarnings("serial")
public class ModelImpl extends UnicastRemoteObject implements ModelInterface{
	private ArrayList<ViewInterface> observers = new ArrayList<ViewInterface>();
	private Board board;
	private Player[] players;
	private int expectedPlayer;
	private Bank monopolyBank;
	private Dice dice;
	private int counter = 0;
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
	public void rollDice() throws RemoteException{
		System.out.println("From model, player has rolled dice.");
		if(!hasRolled){
			hasRolled = true;
	
			// Determine who is the current Player
			currPlayer = players[counter%expectedPlayer];
			
			int steps = dice.roll();
			
			msg = ""+currPlayer.getName()+" rolled: "+steps+'\n';
			move(steps);
			// The player has rolled disable the roll button!
			disableCurrViewRollButton();
			// The player has rolled enable the end turn button
			enableCurrViewEndTurnButton();
		}
		notifyAllObserversOfMsg();
		notifyAllObserversOfBoard();
	}

	/*
	 * Manipulate Observers
	 */
	
	private void disableCurrViewRollButton() throws java.rmi.RemoteException{
		observers.get(counter%expectedPlayer).disableRoll();
	}
	
	private void disableCurrViewEndTurn() throws java.rmi.RemoteException{
		observers.get(counter%expectedPlayer).disableEndTurn();
	}
	
	private void enableCurrViewEndTurnButton() throws java.rmi.RemoteException{
		observers.get(counter%expectedPlayer).enableEndTurn();
	}
	
	private void setControlsTo(boolean state) throws java.rmi.RemoteException{
		// Enable controls for a specific view
		ViewInterface currInterface = observers.get(counter%expectedPlayer);
		currInterface.setAllButtonsTo(state);
	}
	
	@Override
	public void notifyAllObserversOfBoard() throws RemoteException {
		for(ViewInterface v : observers){
			v.updateBoard();
		}
	}
	
	@Override
	public void notifyAllObserversOfMsg() throws RemoteException{
		for(ViewInterface v : observers){
			v.update();
		}
	}
	
	@Override
	public void move(int steps) throws RemoteException{
		// Tell the board to Move the player's token 
			board.move(steps,currPlayer.getToken());
			Square currLoc = currPlayer.getToken().getLoc();
			msg+=""+currPlayer.getName()+" is now on: "+currLoc.getName()+"\n";
	}
	
	@Override
	public Board getBoard() throws RemoteException {
		return this.board;
	}
	
	@Override
	public Player[] getPlayers()throws RemoteException{
		 return players;
	}
	
	@Override
	public void startGame()throws RemoteException{
		// Start the game by setting the current player
		msg = "Welcome to Monopoly Game!\n";
		msg += "Turn: ";
		currPlayer = players[0];
		msg += currPlayer.getName()+", Location: " + currPlayer.getToken().getLoc().getName()+'\n';
		msg += "Account: $"+monopolyBank.getBalance(currPlayer)+'\n';
		notifyAllObserversOfMsg();// Display who's turn it is
		notifyAllObserversOfBoard();// all tokens should be on board.
		setControlsTo(true);// enable controls for current player
	}
	
	@Override
	public void addPlayer(String name) throws java.rmi.RemoteException{
		// Add player to game
		System.out.println("Adding "+name+" to game!");
		System.out.println("cs414.a5.Model: Adding: "+name+" to the game.");
		Player p = new Player(counter,name,allTokens[counter]);  //nullPointer exception
		allTokens[counter].setLoc(board.getStart());
		players[counter] = p;
		counter++;
		monopolyBank.addClient(p);
		int currentPlayer = counter;
		if(currentPlayer == expectedPlayer){
			System.out.println("All players added to game.");
			this.startGame();
		}
	}
	
	public void setExpectedPlayer(int num){
		this.expectedPlayer = num;
		System.out.println("Setting expec p = "+expectedPlayer);
	}

	//HJ: Feel free to del after checking
	@Override
	public void buyDeed() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endTurn() throws RemoteException {
		// disable current players control
		setControlsTo(false);
		disableCurrViewEndTurn();
		
		// Advance to next player
		counter++;
		currPlayer = players[counter%expectedPlayer];
		msg="Turn: "+currPlayer.getName()+" Location: "+currPlayer.getToken().getLoc().getName()+'\n';
		hasRolled = false;
		setControlsTo(true);// enable the new players controls
		notifyAllObserversOfMsg();// Display who's turn it is now
	}

	@Override
	public HashSet<Square> getDeeds() throws RemoteException {
		 return currPlayer.getMyDeeds();
	}
	
	@Override
	public int getNumPlayer() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void auction(Square s, int[] bits) throws RemoteException {
		// TODO Auto-generated method stub	
	}

	@Override
	public Object endGame() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void sellDeed(Square myDeed) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void buildHouse(Square myDeed) throws RemoteException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void buildHotel(Square myDeed) throws RemoteException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mortgage(Square myDeed) throws RemoteException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void umMortgage(Square myDeed) throws RemoteException{
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getMsg() throws RemoteException {
		return msg;
	}
}