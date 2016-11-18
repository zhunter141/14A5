package cs414.a5;

import java.awt.Color;

public class Utility extends Square{
	private static final long serialVersionUID = 1L;
	
	public Utility(Color color, String name, int cost, int rentCost){
		super(color,name);
		super.cost = cost;
		super.rentCost = rentCost;
		this.setPurchasable(true);
	}
}
