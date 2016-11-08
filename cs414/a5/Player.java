package cs414.a5;

import java.util.HashSet;

public class Player {
	private int id;
	private String name;
	private HashSet<Square> myDeeds;
	private Token token;
	
	private Model monopolyModel;
	private Bank monopolyBank;

	
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
	
	
	public String selldeed(Square d){
				String msg = "";
				Player currPlayer = monopolyModel.getCurrPlayer();
				// In this method, deed is a utility, railroad, deed
				//Pay attention on choose deed
				//removeDeeds()
				currPlayer.removeDeed(d);
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
				monopolyBank.deposit(currPlayer,cost);
				msg ="Adding: $"+cost+" to "+currPlayer.getName()+" account!";
				msg +="My properties: "+ currPlayer.toString()+'\n';
				msg +="Account: "+ monopolyBank.getBalance(currPlayer)+"\n";
				return msg;
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
