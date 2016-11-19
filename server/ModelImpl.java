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
	private int[] allBits;
	private int bidIndex;
	
	public ModelImpl(int numPlayers) throws RemoteException {
		super();	
		board = new Board();
		board.initialize();
		allTokens = new Token[numPlayers];		
		dice = new Dice();
		monopolyBank = new Bank();
		board.initCards();
		msg = "";
		players = new Player[numPlayers];
		counter = 0;
		createTokens();
		hasRolled = false;
		allBits = new int[numPlayers];
		bidIndex = 0;
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
			rentChecker();
			taxChecker();
			cardChecker();
			// If player was charged show now
			msg+=currPlayer.getName()+" Account: $"+monopolyBank.getBalance(currPlayer)+"\n";
			
			// The player has rolled disable the roll button!
			disableCurrViewRollButton();
			
			// The player has rolled enable the end turn button
			enableCurrViewEndTurnButton();
		}
		notifyAllObserversOfMsg();
		notifyAllObserversOfBoard();
	}
	
	@Override
	public void move(int steps) throws RemoteException{
		// Tell the board to Move the player's token 
		board.move(steps,currPlayer.getToken());
		Square currLoc = currPlayer.getToken().getLoc();
		msg+=""+currPlayer.getName()+" is now on: "+currLoc.getName()+"\n";
	}
	
	private void rentChecker() throws RemoteException{
		Square currPosition = currPlayer.getToken().getLoc();
		
		if(currPosition.getOwner()!=null && currPosition.getOwner()!=currPlayer){
			//pay rent

			int cost = currPosition.getRentCost();

			if(monopolyBank.payDue(currPlayer, cost)){
				monopolyBank.deposit(currPosition.getOwner(), cost);
				msg += ""+currPlayer.getName()+" paid rent $"+cost+ " to "+currPosition.getOwner().getName()+"\n";
			}
			else{
				msg += "Not enough money to pay rent/taxes\n";
			}	
		}
	}
	
	private void taxChecker() throws java.rmi.RemoteException{
		String playerLoc = currPlayer.getToken().getLoc().getName();
		if(playerLoc.equals("GO TO JAIL")){
			monopolyBank.payDue(currPlayer, 200);
			msg += "Oh, no!";
			//move to jail
			goToJail();
			//endTurn();	
		}
		if(playerLoc.equals("INCOME TAX")){
			monopolyBank.payDue(currPlayer, 200);
		}
		if(playerLoc.equals("LUXURY TAX")){
			monopolyBank.payDue(currPlayer, 300);
		}
	}
	
	private void cardChecker() throws java.rmi.RemoteException{
		Square currPosition = currPlayer.getToken().getLoc();
		if(currPosition.getName().equals("COMMUNITY CHEST") ){
			Card c = board.comDeck.drawCard();
			if(c.getDescription().equals("Get out of jail free")){
				currPlayer.setHasCard(true);	
			}
			msg+=c.processCard(this);
		}
		if(currPosition.getName().equals("CHANCE")){
			Card c = board.chanceDeck.drawCard();
			if(c.getDescription().equals("Get out of jail free")){
				currPlayer.setHasCard(true);
			}
			msg+=c.processCard(this);
		}
		
	}
	
	
	@Override
	public void buyDeed() throws java.rmi.RemoteException{
		Square myLoc = currPlayer.getToken().getLoc();
		
		if(!myLoc.isPurchasable()){
			msg = "You cannot purchase: "+myLoc.getName()+"\n";
			notifyAllObserversOfMsg();
			return;
		}
		else{
			int costOfDeed = myLoc.getCost();
			// CHECK THE PLAYER CAN AFFORD TO PURCHASE DEED
			msg = "This is the price of "+myLoc.getName()+" $"+costOfDeed+"\n";
			
			if(monopolyBank.payDue(currPlayer, costOfDeed) == false){
				msg += "Bank: "+currPlayer.getName()+" does not have enough money!\n";
			}
			else{
				currPlayer.addDeed(myLoc);
				myLoc.setOwner(currPlayer);
				myLoc.setPurchasable(false);
				msg +="Successfull purchased: "+myLoc.getName()+"! \n";
				msg +="It has been added your list of deeds.\n";
				msg +="Account: $"+monopolyBank.getBalance(currPlayer)+"\n";
			}
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
	public void startAuction(Square s) throws RemoteException {
		for(ViewInterface v : observers){
			//v.auctionMenu(s);
		}
		 auction(s, allBits) ;
	}
	
	@Override
	public Board getBoard() throws RemoteException {
		System.out.println("Returning board.");
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
	public int getNumPlayer()throws RemoteException{
		return counter;
	}

	@Override
	public void auction(Object o,int[] bits) throws RemoteException{
		 msg = monopolyBank.auction(o,bits,players,currPlayer);
		 msg += currPlayer.getName()+", Location: " + currPlayer.getToken().getLoc().getName()+'\n';
		 msg += "Account: $"+monopolyBank.getBalance(currPlayer)+'\n';
		 notifyAllObserversOfMsg();
		 notifyAllObserversOfBoard();
	 }

	
	@Override
	public void enterBid(Square s ,int bit) throws java.rmi.RemoteException{
		allBits[this.bidIndex+1] = bit; 
	}
	
	@Override
	public String endGame(){
		 Player winner = players[0];
		 for(int i=1;i<counter;i++){
			 if(monopolyBank.getBalance(winner) < monopolyBank.getBalance(players[i])){
				 winner = players[i];
			 }
		 }
		 return winner.getName()+" is the winner! Final amount: $"+monopolyBank.getBalance(winner);
	 }
	
	@Override
	public void sellDeed(Square d) throws RemoteException{
		msg += currPlayer.selldeed(d, monopolyBank);
		notifyAllObserversOfMsg();
		notifyAllObserversOfBoard();
	}
	
	@Override
	public void buildHouse(Square myDeed) throws RemoteException{

		msg += myDeed.buildHouse( currPlayer,  monopolyBank);
		msg +="My money: $"+ monopolyBank.getBalance(currPlayer)+"\n";
		
		notifyAllObserversOfMsg();
		notifyAllObserversOfBoard();
	}
	
	@Override
	public void buildHotel(Square myDeed) throws RemoteException{
		msg += myDeed.buildHouse( currPlayer,  monopolyBank);
		msg +="My money: $"+ monopolyBank.getBalance(currPlayer)+"\n";
		
		notifyAllObserversOfMsg();
		notifyAllObserversOfBoard();		
	}
	
	@Override
	public void mortgage(Square myDeed) throws RemoteException{
		if(myDeed instanceof Deed ){
			Deed deed =  (Deed)myDeed;
			if(deed.hasBuilding() == false && deed.isMortgagable() == false){
				monopolyBank.deposit(currPlayer, (int) (0.5*deed.getCost()));
				deed.setMortgage(true);
				msg += "Succesfully mortgaged: "+myDeed.getName()+"\n";
			}
			else{
				msg += "You can't mortgage it, because there is a building.\n"; 
			}
		}
		else{
			msg += "You can't mortgage it, it is not a deed!\n";
		}
		msg +="My money: $"+ monopolyBank.getBalance(currPlayer)+"\n";
		notifyAllObserversOfMsg();
		notifyAllObserversOfBoard();	
	}
	
	@Override
	public void umMortgage(Square myDeed) throws RemoteException{
		if(!(myDeed instanceof Deed)){
			msg += "Can not be unmortgaged."+'\n'; 
		}
		else{
			Deed deed =  (Deed)myDeed;
			
			if(deed.isMortgagable() == true){
				if(monopolyBank.payDue(currPlayer, (int)(1.1*deed.getCost())) == true){
					deed.setMortgage(false);
					msg += "Succesfully ummortgaged it"+'\n';
				}
				else{
					msg += "Failure to mortgage because not enough money."+'\n';
				}
			}
			else{
				msg += "It is not mortgaged yet."+'\n'; 
			}
		}
		msg +="Account: $"+ monopolyBank.getBalance(currPlayer)+'\n';
		notifyAllObserversOfMsg();
		notifyAllObserversOfBoard();	
	}
	
	@Override
	public void goToJail(){
		//move to jail -> may be refactor later
		if(currPlayer.hasCard() == false){
			board.move(20,currPlayer.getToken());
			Square currLoc = currPlayer.getToken().getLoc();
			msg=""+currPlayer.getName()+" is now on: "+currLoc.getName()+'\n';
			msg+="My properties: "+ currPlayer.toString()+'\n';
			msg+="My money: "+ monopolyBank.getBalance(currPlayer)+'\n';
			//view.update();
			//view.updateBoard();
		}else{
			msg += "You used your Get out of jail free card.\n";
			currPlayer.setHasCard(false);
		}
	}
	
	@Override
	public String getMsg() throws RemoteException {
		return msg;
	}

	@Override
	public Player getCurrPlayer() throws RemoteException {
		//return currPlayer;
		return players[counter%expectedPlayer];
	}

	@Override
	public Bank getBank() throws RemoteException {
		return monopolyBank;
	}

}