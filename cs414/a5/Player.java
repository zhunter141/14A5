package cs414.a5;

import java.util.HashSet;

public class Player {
	private int id;
	private String name;
	private HashSet<Square> myDeeds;
	private Token token;
	
	//private Model monopolyModel;
	//private Bank monopolyBank;

	
	public Player(int id, String name, Token token) {
		this.id = id;
		this.name = name;
		this.token = token;
		myDeeds = new HashSet<Square>();
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
		myDeeds.add(d);
	} 
	
	public void removeDeed(Square d){
		myDeeds.remove(d);
	}
	
	
	public String selldeed(Square d,Bank monopolyBank){
				String msg = "";
				// In this method, deed is a utility, railroad, deed
				//Pay attention on choose deed
				//removeDeeds()
				this.removeDeed(d);
				int cost = 0;
				if(d instanceof Utility){
					Utility utility =  (Utility)d;
					d =  (Utility)d;
					cost = utility.getCost();
					//Just in case
					utility.setOwner(null);
				}
				else if(d instanceof Deed){
					Deed deed =  (Deed)d;
					d =  (Deed)d;
					cost = deed.getCost();
					//Just in case
					deed.setOwner(null);
					
					if(deed.hasBuilding() == true){
						deed.setExistanceOfHouseHotel(false);
						deed.setExistanceOfHotel(false);
						deed.setExistanceOfHouse(false);

						if(deed.hasHotel() == true){
							cost += deed.getHotelCost();
						}
						else{
							cost += deed.getHouseCost();
						}	
					}
				}
				// Square must be a RailRoad
				else{
					RailRoad railRoad =  (RailRoad)d;
					d =  (RailRoad)d;
					cost = railRoad.getCost();
					//Just in case
					railRoad.setOwner(null);
				}
				// Update player account
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
}
