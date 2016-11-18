package cs414.a5;

import java.io.Serializable;
import java.util.HashSet;

public class Player implements Serializable{
	private int id;
	private String name;
	private HashSet<Square> myDeeds;
	private HashSet<Card> myCards;
	private Token token;
	private boolean hasCard;
	private static final long serialVersionUID = 1L;
	
	public Player(int id, String name,Token token) {
		this.id = id;
		this.name = name;
		this.token = token;
		this.hasCard = false;
		myDeeds = new HashSet<Square>();
		myCards = new HashSet<Card>();
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public Token getToken(){
		return token;
	}
	
	public HashSet<Square> getMyDeeds(){
		return myDeeds;
	}
	
	public void addDeed(Square d){
		System.out.println("Adding "+d.getName()+" to my deeds.");
		System.out.println("Did I add "+d.getName()+"="+myDeeds.add(d));
	} 
	
	public void removeDeed(Square d){
		System.out.println("Removing "+d.getName()+" from my deeds.");
		System.out.println("Did I remove "+d.getName()+"="+myDeeds.remove(d));
	}
	
	public void addCard(Card c){
		myCards.add(c);
		setHasCard(true);
	}
	
	public void removeCard(){
		myDeeds.remove(myCards.iterator().next());
		setHasCard(false);
	}
	
	public String selldeed(Square d,Bank monopolyBank){
				// In this method, deed is a utility, railroad, deed
				//Pay attention on choose deed
				//removeDeeds()
				this.removeDeed(d);
				int cost = d.getCost();
				d.setOwner(null);
				
				// Update player account
				String msg = "";
				monopolyBank.deposit(this,cost);
				msg ="Adding: $"+cost+" to "+this.getName()+" account!";
				msg +="My properties: "+ this.toString()+'\n';
				msg +="Account: $"+ monopolyBank.getBalance(this)+"\n";
				return msg;
	}

	public int buyDeed(Bank monopolyBank,Square myLoc){
		int costOfDeed;
		// The square is purchasable because it is not own by anyone
		// determine the cost of the square
		// Implied 'else'
		if(myLoc instanceof Utility){
			Utility util =  (Utility)myLoc;
			costOfDeed = util.getCost();
		}
		else if(myLoc instanceof Deed){
			Deed deed =  (Deed)myLoc;
			costOfDeed = deed.getCost();
		}
		// Square MUST be RailRoad
		else{
			RailRoad railRoad =  (RailRoad)myLoc;
			costOfDeed = railRoad.getCost();
		}
		return costOfDeed;
		
	}
	
	@Override
	public String toString(){
		String listOfDeeds = ""; 
		for(Square s: myDeeds){
			listOfDeeds = listOfDeeds+" "+s.getName();
		}
		return listOfDeeds;
	}

	public boolean hasCard() {
		return this.hasCard;
	}
	
	public void setHasCard(boolean b) {
	    this.hasCard = b;
	}
}