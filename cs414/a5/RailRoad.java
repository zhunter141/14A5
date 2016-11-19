package cs414.a5;

import java.awt.Color;

public class RailRoad extends Square{
	private static final long serialVersionUID = 1L;

	public RailRoad(Color color, String name,int cost, int rentCost) {
		super(color, name);
		super.cost = cost;
		super.rentCost = rentCost;
		super.setPurchasable(true);
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getRentCost(){
		return rentCost;
	}
}
