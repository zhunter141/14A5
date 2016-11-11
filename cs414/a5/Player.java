package cs414.a5;

import java.util.HashSet;

public class Player {
	private int id;
	private String name;
	private HashSet<Square> myDeeds;
	private Token token;
	
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
		//this.token = token;
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
	@Override
	public String toString(){
		String listOfDeeds = ""; 
		for(Square s: myDeeds){
			listOfDeeds = listOfDeeds+" "+s.getName();
		}
		return listOfDeeds;
		
	}
	
	public void setName(String s){
		name = s;
	}
}
