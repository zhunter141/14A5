package cs414.a5;

public class Card {
	
	private String description;
	private String action;
	private int val;
	
	public Card(String description, String action, int val) {
		this.description = description;
		this.action = action;
		this.val = val;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public String getAction() {
		return action;
	}
	
	public int getVal() {
		return val;
	}
	
	
	
	// Card move5squares = new Card("Move 5 squares ahead!", "move", 5);
	
	// Constructor for board needs to change to use cards:
	// public Board(ITile[] tiles, CardDeck chanceDeck, CardDeck communityChestDeck) {
	
	// Model need method to check if there is a card in a Square

}
