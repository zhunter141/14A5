package cs414.a5;

import java.awt.Color;

public class Deed extends Square {
	private static final long serialVersionUID = 1L;
	private int houseCost;
	private int hotelCost;
	private int houseIndex ;
	private int hotelIndex ;
	private boolean hasBuilding;
	private boolean hasHouse;
	private boolean hasHotel;
	private boolean isMortgaged;
	private Object[] houseContainer= new Object[4];
	private Object[] hotelContainer= new Object[1];

	public Deed(Color color, String name,int cost,int houseCost,int hotelCost, int rentCost) {
		super(color,name);
		this.houseIndex = 0;
		this.hotelIndex = 0;

		super.cost = cost;
		super.rentCost = rentCost;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
		super.setPurchasable(true);
		this.setExistanceOfHouseHotel(false);
		this.setExistanceOfHouse(false);
		this.setExistanceOfHotel(false);
	}
	
	public int getHouseCost(){
		return houseCost;
	}

	public int getHotelCost(){
		return hotelCost;
	}
	
	public void setOwner(Player o){
		System.out.println("Change of ownership, player class.");
		if (o == null){
			System.out.println("Game owns the deed now.");
			super.setPurchasable(true);
			super.setOwner(null);
		} 
		else{
			System.out.println(o.getName()+" owns the deed now.");
			super.setPurchasable(false);
			super.setOwner(o);
		}
	}
	
	public int getHouseNum(){
		return houseIndex;
	}
	
	public int getHotelNum(){
		return hotelIndex;
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
	
	public boolean addHotel() {
		
		if(this.hotelIndex==1){
			return false;
		}
		else{
			//Arbitrary hotel;
			Hotel h = new Hotel("H", 80, 80, Color.BLACK);
		//1 means has hotel
		this.hotelIndex += 1;
		this.hotelContainer[hotelIndex-1]= h;
		
		//Destroy 4 houses
		 Object[] houseNewContainer= new Object[4];
		 houseContainer = houseNewContainer;
		return true;
		}		
	}
	
	public boolean addHouse() {
		if(this.houseIndex ==4){
			return false;
		}
		else{
			//Arbitrary house;
			House h = new House("H", 80, 80);
		//1 means has house
		this.houseIndex += 1;
		this.houseContainer[houseIndex-1]= h;

		return true;
		}
	}
	
	public boolean isMortgagable() {
		return isMortgaged;
	}

	public void updateRent() {
		
	}

	public void updateRentHotel() {
		//this.rentCost = (int) (rentCost*2);
	}	
	
	@Override
	public int getCost(){
		int cost = super.getCost();
		
		if(hasBuilding() == true){
			setExistanceOfHouseHotel(false);
			setExistanceOfHotel(false);
			setExistanceOfHouse(false);

			if(hasHotel() == true){
				cost += getHotelCost();
			}
			else{
				cost += getHouseCost();
			}	
		}
		return cost;
	}
}