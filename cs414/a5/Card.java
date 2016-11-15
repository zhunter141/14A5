package cs414.a5;

public class Card {
	
	private String description;
	private String action;
	private int val;
	private Player owner;

	
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

			theModel.deposit(currentPlayer, amount);


			
		}else if (getAction().equals("pay")){
			int amount = this.getVal();
			
			theModel.payDue(currentPlayer, amount);


			
		}else if (getAction().equals("move")){
			int distance = this.getVal();
			theModel.move(distance);


			
		}else if (getAction().equals("toJail")){//&
			Square sqtmp = theModel.board.getSquares().get("JAIL");
			Token t = theModel.getCurrPlayer().getToken();
			t.setLoc((Square)sqtmp);
			t.getLoc().removeToken(t);
			if(currentPlayer.hasCard() == true){
				
			}
			else{
				theModel.endTurn();
			}



		}else if (getAction().equals("outJail")){
			String msg = "You used your Get out of jail free card.";
			currentPlayer.setHasCard(false);


		}else if (getAction().equals("GO")){
			Square sqtmp = (Square)(theModel.board.getSquares().get("GO"));
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);


		}else if (getAction().equals("MAYFAIR")){
			Square sqtmp = theModel.board.getSquares().get("MAYFAIR");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);


		}else if (getAction().equals("TRAFLGAR SQUARE")){
			Square sqtmp = theModel.board.getSquares().get("TRAFLGAR SQUARE");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			

		}else if (getAction().equals("MARYLEBONE STATION")){
			Square sqtmp = theModel.board.getSquares().get("MARYLEBONE STATION");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			



		}else if (getAction().equals("PALL MALL")){
			Square sqtmp = theModel.board.getSquares().get("PALL MALL");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			


		}else if (getAction().equals("OLD KENT ROAD")){
			Square sqtmp = theModel.board.getSquares().get("OLD KENT ROAD");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			

			

		}
		theModel.view.updateBoard();


	}
	

}
