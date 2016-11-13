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
		if (getAction().equals("collect")){//&
			int amount = this.getVal();

			theModel.deposit(currentPlayer, amount);
			System.out.println(this.description+"1");


			
		}else if (getAction().equals("pay")){
			int amount = this.getVal();
			
			theModel.payDue(currentPlayer, amount);
			System.out.println(this.description+"2");

			
		}else if (getAction().equals("move")){
			int distance = this.getVal();
			theModel.move(distance);
			System.out.println(this.description+"3");

			
		}else if (getAction().equals("toJail")){//&
			Square sqtmp = theModel.board.getSquares().get("JAIL");
			currentPlayer.getToken().setLoc((Square)sqtmp);
			if(currentPlayer.hasCard() == true){
				
			}
			else{
				theModel.endTurn();
			}
			System.out.println(this.description+"4");


		}else if (getAction().equals("outJail")){
			String msg = "You used your Get out of jail free card.";
			currentPlayer.setHasCard(false);
			System.out.println(this.description+"5");

			
		}else if (getAction().equals("GO")){
			Square sqtmp = (Square)(theModel.board.getSquares().get("GO"));
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);
			System.out.println(this.description+"6");
			System.out.println(theModel.getCurrPlayer().getToken().getLoc().getName());

		}
		else if (getAction().equals("MAYFAIR")){
			Square sqtmp = theModel.board.getSquares().get("MAYFAIR");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);
			System.out.println(this.description+"7");
			System.out.println(theModel.getCurrPlayer().getToken().getLoc().getName());

		}else if (getAction().equals("TRAFLGAR SQUARE")){
			Square sqtmp = theModel.board.getSquares().get("TRAFLGAR SQUARE");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			System.out.println(this.description+"8");
			System.out.println(theModel.getCurrPlayer().getToken().getLoc().getName());

		}else if (getAction().equals("MARYLEBONE STATION")){
			Square sqtmp = theModel.board.getSquares().get("MARYLEBONE STATION");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			System.out.println(this.description+"9");
			System.out.println(theModel.getCurrPlayer().getToken().getLoc().getName());


		}else if (getAction().equals("PALL MALL")){
			Square sqtmp = theModel.board.getSquares().get("PALL MALL");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			System.out.println(this.description+"10");
			System.out.println(theModel.getCurrPlayer().getToken().getLoc().getName());

		}else if (getAction().equals("OLD KENT ROAD")){
			Square sqtmp = theModel.board.getSquares().get("OLD KENT ROAD");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			System.out.println(this.description+"11");
			System.out.println(theModel.getCurrPlayer().getToken().getLoc().getName());
			

		}
		theModel.view.updateBoard();


	}
	
	
	// Card move5squares = new Card("Move 5 squares ahead!", "move", 5);
	
	// Constructor for board needs to change to use cards:
	// public Board(ITile[] tiles, CardDeck chanceDeck, CardDeck communityChestDeck) {
	
	// Model need method to check if there is a card in a Square

}
