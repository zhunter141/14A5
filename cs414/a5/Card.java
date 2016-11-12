package cs414.a5;

public class Card {
	
	private String description;
	private String action;
	private int val;
	private Bank bank;
	private Board board;
	private Model model;
	
	public Card(String description, String action, int val) {
		this.description = description;
		this.action = action;
		this.val = val;
		bank = new Bank();
		board = new Board();
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
	
	public void processCard(Model theModel){
		Board theBoard = theModel.getBoard();
		Player currentPlayer = theModel.getCurrPlayer();
		
		String despMsg = getDescription();
		if (getAction().equals("collect")){
			int amount = getVal();
			bank.deposit(currentPlayer, amount);
			
		}else if (getAction().equals("pay")){
			int amount = getVal();
			bank.payDue(currentPlayer, amount);
			
		}else if (getAction().equals("move")){
			int distance = getVal();
			board.move(distance, currentPlayer.getToken());
			
		}else if (getAction().equals("toJail")){
			
			model.goToJail();
			
		}else if (getAction().equals("outJail")){
			System.out.println("Out of jail");
			
		}else if (getAction().equals("loc")){
			int amount = getVal();
			
		}
	}
	
	
	// Card move5squares = new Card("Move 5 squares ahead!", "move", 5);
	
	// Constructor for board needs to change to use cards:
	// public Board(ITile[] tiles, CardDeck chanceDeck, CardDeck communityChestDeck) {
	
	// Model need method to check if there is a card in a Square

}