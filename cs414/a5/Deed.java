package cs414.a5;

import java.awt.Color;

public class Deed extends Square {
	private int cost;
	private int houseCost;
	private int hotelCost;
	private Player owner;
	private int rentCost;
	private boolean hasBuilding;
	private boolean hasHouse;
	private boolean hasHotel;
	private boolean isMortgaged;


	
	public Deed(Color color, String name,int cost,int houseCost,int hotelCost, int rentCost) {
		super(color,name);
		this.cost = cost;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
		owner = null;
		this.setPurchasable(true);
		this.rentCost = rentCost;
		this.setExistanceOfHouseHotel(false);
		this.setExistanceOfHouse(false);
		this.setExistanceOfHotel(false);
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getHouseCost(){
		return houseCost;
	}

	public int getHotelCost(){
		return hotelCost;
	}
	
	public void setOwner(Player o){
		owner = o;
		if (o == null){
			this.setPurchasable(true);
		} 
		else{
			this.setPurchasable(false);
			}
	}
	
	public int getRentCost(){
		return rentCost;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public void setExistanceOfHouseHotel(boolean b){
		hasBuilding = b;
	}
	
	public void setExistanceOfHouse(boolean b){
		hasHouse = b;
	}
	
	public void setExistanceOfHotel(boolean b){
		hasHotel = b;
	}
	public void setMortgage(boolean b){
		isMortgaged = b;
	}
	
	public boolean hasBuilding(){
		return hasBuilding;
	}
	
	public boolean hasHouse(){
		return hasHouse;
	}

	public boolean hasHotel(){
		return hasHotel;
	}

	public boolean isMortgagable() {
		return isMortgaged;
	}
	
}
