package cs414.a5;

import java.awt.Color;
import java.util.ArrayList;

public class Card {
 	private String name;
 	
	public Card(String name){
 		this.name = name.toUpperCase();
 	}
	// Getters
	
	
	public String getName(){
		return name;
	}
	
	 
	
	public Player getOwner(){
		return null;
	}
	
	 
	
	public void setOwner(Player p){
		// Do nothing because regular squares cannot be owned by anyone
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Card){
			return ((Card) o).getName().equals(this.getName());
		}
		return false;
	}
	
	 
}
