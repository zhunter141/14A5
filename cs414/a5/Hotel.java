package cs414.a5;

import java.awt.Color;

public class Hotel {
	private int cost;
	private int rentCost;
	private Color color;
	
	public Hotel( String name,int cost, int rentCost, Color color) {
		this.cost = cost;
		this.color = color;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	
}
