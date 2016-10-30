package cs414.a5;

public class Token {
	private String description;
	private Square currLoc;
	
	public Token(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Square getLoc(){
		return currLoc;
	}
	
	public void setLoc(Square loc){
		this.currLoc = loc;
		this.currLoc.addToken(this);
	}
	
	public void move(){
		currLoc.removeToken(this);
		currLoc = currLoc.getNext();
		currLoc.addToken(this);
	}
}
