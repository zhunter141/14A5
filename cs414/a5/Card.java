package cs414.a5;

public class Card {
	
	private String description;
	private String action;
	private int val;
	private Bank bank;
	private Board board;
	private Model model;
	private Token token;
	private Player owner;

	
	public Card(String description, String action, int val) {
		this.description = description;
		this.action = action;
		this.val = val;
		bank = new Bank();
		board = new Board();
		//board.initialize();
		//board.initCards();
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getAction() {
		return action;
	}
	
	public int getVal() {
		return this.val;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	public void setOwner(Player p) {
		this.owner = p;
	}
	public void processCard(Model theModel){
		Board theBoard = theModel.getBoard();
		Player currentPlayer = theModel.getCurrPlayer();
		
		String despMsg = getDescription();
		if (getAction().equals("collect")){
			int amount = this.getVal();
			bank.deposit(currentPlayer, amount);
			
		}else if (getAction().equals("pay")){
			int amount = this.getVal();
			bank.payDue(currentPlayer, amount);
			
		}else if (getAction().equals("move")){
			int distance = this.getVal();
			board.move(distance, currentPlayer.getToken());
			
		}else if (getAction().equals("toJail")){
			Object sqtmp = board.getSquares().get("GO TO JAIL");
			currentPlayer.getToken().setLoc((Square)sqtmp);
			if(currentPlayer.hasCard() == true){
				
			}
			else{
				theModel.endTurn();
			}

		}else if (getAction().equals("outJail")){
			String msg = "You used your Get out of jail free card.";
			currentPlayer.removeCard();
			
		}else if (getAction().equals("GO")){
			Object sqtmp = board.getSquares().get("GO");
			token.setLoc((Square) sqtmp);	
		}
		else if (getAction().equals("MAYFAIR")){
			Object sqtmp = board.getSquares().get("MAYFAIR");
			token.setLoc((Square) sqtmp);	
		}else if (getAction().equals("TRAFLGAR SQUARE")){
			Object sqtmp = board.getSquares().get("TRAFLGAR SQUARE");
			token.setLoc((Square) sqtmp);	
		}else if (getAction().equals("MARYLEBONE STATION")){
			Object sqtmp = board.getSquares().get("MARYLEBONE STATION");
			token.setLoc((Square) sqtmp);	
		}else if (getAction().equals("PALL MALL")){
			Object sqtmp = board.getSquares().get("PALL MALL");
			token.setLoc((Square) sqtmp);	
		}else if (getAction().equals("OLD KENT ROAD")){
			Object sqtmp = board.getSquares().get("OLD KENT ROAD");
			token.setLoc((Square) sqtmp);	
		}

	}
	
	
	// Card move5squares = new Card("Move 5 squares ahead!", "move", 5);
	
	// Constructor for board needs to change to use cards:
	// public Board(ITile[] tiles, CardDeck chanceDeck, CardDeck communityChestDeck) {
	
	// Model need method to check if there is a card in a Square

}
