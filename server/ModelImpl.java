package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;

import common.ModelInterface;
import cs414.a5.Bank;
import cs414.a5.Board;
import cs414.a5.Deed;
import cs414.a5.Dice;
import cs414.a5.Player;
import cs414.a5.Square;
import cs414.a5.Token;
import cs414.a5.View;

public class ModelImpl extends UnicastRemoteObject implements ModelInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player[] players;
	private Token[] allTokens;
	private Board board;
	private Bank monopolyBank;
	private Dice dice;
	private int counter;
	private int iterator;
	private Player currPlayer;
	private String msg;
	private View view;
	private transient boolean hasRolled;
	
	public ModelImpl()throws java.rmi.RemoteException{
		// initialize game objects
		board = new Board();
		dice = new Dice();
		monopolyBank = new Bank();
		board.initialize();
		board.initCards();
		msg = "";
		players = new Player[4];
		allTokens = new Token[4];
		iterator = 0;
		counter = 0;
		createTokens();
		hasRolled = false;
		
	}

	private void createTokens(){
	}

	public void addView(View v){
		view = v;
	}
	
	public Player getCurrPlayer(){
		return currPlayer;
	}
	
	public int getNumPlayer(){
		return counter;
	}
	
	// Respond to Controller button presses
	
	public void startGame(){
		// Start the game by setting the current player
		msg = "Welcome to Monopoly Game!\n";
		msg += "Turn: ";
		currPlayer = players[0];
		msg += currPlayer.getName()+", Location: " + currPlayer.getToken().getLoc().getName()+'\n';
		msg += "Account: $"+monopolyBank.getBalance(currPlayer)+'\n';
		view.update();
	}
	
	public void rollDice(){
		if(hasRolled == true){
			msg = "You cannot roll more than once per turn!\n";
		}
		else{
			// A player can roll dice
			// Determine who is the current Player
			currPlayer = players[iterator%counter];
			int steps = dice.roll();
			msg = ""+currPlayer.getName()+" rolled: "+steps+'\n';
			move(steps);
			hasRolled = true;
			// The player has rolled disable the roll button!
			view.disableRoll();
			// The player has rolled enable the end turn button
			view.enableEndTurn();
		}
		view.update();
	}
	
	private void move(int steps){
		
	}
	
	public void buildHouse(Square s){
		msg += s.buildHouse( currPlayer,  monopolyBank);
		msg +="My money: $"+ monopolyBank.getBalance(currPlayer)+"\n";
		
		view.update();
	}
	
	public void buildHotel(Square s){
	}
	
	public void endTurn(){
		iterator++;
		currPlayer = players[iterator%counter];
		msg="Turn: "+currPlayer.getName()+" Location: "+currPlayer.getToken().getLoc().getName()+'\n';
		hasRolled = false;
		view.enableRoll();
		view.disableEndTurn();
		view.update();
	}
	
	public void addPlayer(String name){
		// Add player to game
		System.out.println("cs414.a5.Model: Adding: "+name+" to the game.");
		Player p = new Player(counter,name,allTokens[counter]);
		allTokens[counter].setLoc(board.getStart());
		players[counter] = p;
		counter++;
		monopolyBank.addClient(p);
	}
	
	
	public void sellDeed(Square d){
		msg += currPlayer.selldeed(d, monopolyBank);
		view.update();

		
	}
	
	public void buyDeed(){
		Square myLoc = currPlayer.getToken().getLoc();
		int costOfDeed;
		// Check myLoc is purchasable
		if(myLoc.isPurchasable() != true){
			msg = "You cannot purchase: "+myLoc.getName()+"\n";
			view.update();
			return;
		}
		costOfDeed = currPlayer.buyDeed(monopolyBank, myLoc);
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
		view.update();
	}
	
	//get status aka give status to view/others
	
	
	 public String EndTheGame(){
		String s = "";
		String p = "";
		for(Player i :players){
			p = "Player: "+i.getName()+ "Balance: "+ monopolyBank.getBalance(i);
			s = s + p;
		}
		return s;	
	}
	
	 public void mortgage(Square s){
		if(s instanceof Deed ){
			Deed deed =  (Deed)s;
			if(deed.hasBuilding() == false && deed.isMortgagable() == false){
				monopolyBank.deposit(currPlayer, (int) (0.5*deed.getCost()));
				deed.setMortgage(true);
				msg += "Succesfully mortgaged: "+s.getName()+"\n";
			}
			else{
				msg += "You can't mortgage it, because there is a building.\n"; 
			}
		}
		else{
			msg += "You can't mortgage it, it is not a deed!\n";
		}
		msg +="My money: $"+ monopolyBank.getBalance(currPlayer)+"\n";
		view.update();
	}
	
	 public void umMortgage(Square s){
		if(!(s instanceof Deed)){
			msg += "Can not be unmortgaged."+'\n'; 
		}
		else{
			Deed deed =  (Deed)s;
			
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
		view.update();
	}
	
	 public void auction(Object o,int[] bits){

		 msg = monopolyBank.auction(o,bits,players, currPlayer);
		 msg += currPlayer.getName()+", Location: " + currPlayer.getToken().getLoc().getName()+'\n';
		 msg += "Account: $"+monopolyBank.getBalance(currPlayer)+'\n';
		 view.update();


	 }
	  
	 public Player[] getPlayers(){
		 return players;
	 }
	 
	 public Token[] getTokens(){
		 return allTokens;
	 }
	
	 public HashSet<Square> getDeeds(){
		 return currPlayer.getMyDeeds();
	 }
	 
	 public Board getBoard(){
		 return board;
	 }
	 
	 public String getMsg(){
		 return msg;
	 }
	 
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
	public void addView(ViewImpl v) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToJail() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
