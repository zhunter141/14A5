package cs414.a4;

import java.awt.Color;

public class RailRoad extends Square{
	private int cost;
	private int rentCost;
	
	public RailRoad(Color color, String name,int cost, int rentCost) {
		super(color, name);
		this.cost = cost;
		this.rentCost = rentCost;
		this.setPurchasable(true);
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getRentCost(){
		return rentCost;
	}
}
