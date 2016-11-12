/* 
 * Team cs414d
 */
package cs414.a5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Square {
	private Color color;
	private String name;
	private boolean isPurchasable;
	private Square next;
	private ArrayList<Token> myTokens;
	
	public Square(Color color,String name){
		this.color = color;
		this.name = name.toUpperCase();
		setPurchasable(false);
		next = null;
		myTokens = new ArrayList<Token>();
	}
	// Getters
	public Color getColor(){
		return color;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isPurchasable(){
		return isPurchasable;
	}
	
	public Square getNext(){
		return next;
	}
	
	public Player getOwner(){
		return null;
	}
	
	// Setters
	public void setNext(Square next){
		this.next = next;
	}
	
	public void setOwner(Player p){
		// Do nothing because regular squares cannot be owned by anyone
	}
	
	public String buildHotel(Player currPlayer, Bank monopolyBank, Square s) {
		String msg = "";
		HashSet<Square>  myDeeds = currPlayer.getMyDeeds();
		if(!(s instanceof Deed)){
			msg = "It is not a deed"+'\n';
		}
		else{
			if(checkColorGroup(s,currPlayer,myDeeds) == 3){
				//build hotel
				Deed d = (Deed)s;
				d.addHotel();
				d.updateRentHotel();
				msg = "Succesfully build a Hotel."+'\n';
						
		
			} 
			else{
				msg = "No enough deeds to build hotel."+'\n';

			}
		}

		return msg;
	}
	
	private int checkColorGroup(Square s2, Player currPlayer, HashSet<Square> myDeeds) {
		Color sqrOriginal = ((Deed)s2).getColor();
		Iterator<Square> i = myDeeds.iterator();
		int c = 0;
		
	
		if(checkHouseHotelNumber((Deed)s2) == false){
			c = 0;
		}
		else{
			c=1;
			while (i.hasNext() == true){
			Square s = i.next();
			if(s instanceof Deed ){
				Deed d = (Deed)s;
				if(sqrOriginal.equals(d.getColor())){
					if(checkHouseHotelNumber(d) == true ){}
					c += 1;
				}
				
			}
		}
		
		}
		return c;
		
	}
	private boolean checkHouseHotelNumber(Deed d) {
		 int houseNum = d.getHouseNum();
		 int hotelNum = d.getHotelNum();

		 if(houseNum == 4 || hotelNum == 1){
			 return true;
		 }
		 else{
			 return false;
		 }
	}
	/*
	private List<Integer> checkColorNum(int[] colorNumBox) {
		List<Integer> res = null ;
		for(int i =0;i<colorNumBox.length;i++){
			if(colorNumBox[i] == 12){
				res.add(colorNumBox[i]);
			}
		}
		return res;
	}
	private int checkColor(Color color2) {
		int res = -1;
		//can't use switch statement and no enum for color
		if(color2.equals(Color.BLACK)){
			res = 1;
		}
		else if(color2.equals(Color.BLUE)){
			res = 2;
		}
		else if(color2.equals(Color.CYAN)){
			res = 3;
		}
		else if(color2.equals(Color.GREEN)){
			res = 4;
		}
		else if(color2.equals(Color.ORANGE)){
			res = 5;
		}		
		else if(color2.equals(Color.PINK)){
			res = 6;
		}
		else if(color2.equals(Color.RED)){
			res = 7;
		}
		else if(color2.equals(Color.YELLOW)){
			res = 8;
		}
		else{}
		
		return res;
	}
	*/
	public String buildHouse(Player currPlayer, Bank monopolyBank){
		String msg = "";
		if(this instanceof Deed ){
		Deed currDeed = (Deed)this;
		//check houseNumber
			if(currDeed.getHotelNum() ==1 || currDeed.getHouseNum() == 4){
				System.out.println(currDeed.getHotelNum()+" "+currDeed.getHouseNum());
				msg += "Can't build more than 4 houses."+'\n';
			}
			else{
			
				if(monopolyBank.payDue(currPlayer, currDeed.getHouseCost()) == false ){
					msg += "No enough money to build a house."+'\n' ;
				}
				else{
				
					//Build it 
					currDeed.setExistanceOfHouseHotel(true);
					currDeed.setExistanceOfHotel(true);				
					currDeed.updateRent();
					currDeed.addHouse();
					msg += "Removing $"+currDeed.getHouseCost()+"from "+currPlayer.getName()+"\n";
					msg += "Succesfully build a house on "+currDeed.getName()+"\n" ;
				
				}
			}
		}
		else{
			msg += "Can't build house here."+'\n' ;
		}
	
		return msg;
}
	
	
	
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Square){
			return ((Square) o).getName().equals(this.getName());
		}
		return false;
	}
	
	public boolean getPurchasable() {
		return isPurchasable;
	}
	
	public void setPurchasable(boolean isPurchasable) {
		this.isPurchasable = isPurchasable;
	}
	
	public void addToken(Token t){
		myTokens.add(t);
	}
	
	public void removeToken(Token t){
		myTokens.remove(t);
	}
	
	public ArrayList<Token> getTokens(){
		return myTokens;
	}
	
}
