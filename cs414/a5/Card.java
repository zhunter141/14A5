package cs414.a5;

import java.io.Serializable;
import java.rmi.RemoteException;

import common.ModelInterface;

public class Card implements Serializable{
	
	private static final long serialVersionUID = 1L; // added by TJ 
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
	
	// should return a msg to view
	public String processCard(ModelInterface theModel) throws RemoteException{
		Player currentPlayer = theModel.getCurrPlayer();
		String despMsg = "";
		despMsg = "Player "+ currentPlayer.getName() + " got card: " + "\"" + getDescription()+ "\"\n";
		
		if (getAction().equals("collect")){
			int amount = this.getVal();
			theModel.getBank().deposit(currentPlayer, amount);
		}else if (getAction().equals("pay")){
			int amount = this.getVal();
			theModel.getBank().payDue(currentPlayer, amount);
		}else if (getAction().equals("move")){
			int distance = this.getVal();
			theModel.move(distance);
		}else if (getAction().equals("toJail")){//&
			Square sqtmp = theModel.getBoard().getSquares().get("JAIL");
			Token t = theModel.getCurrPlayer().getToken();
			t.setLoc((Square)sqtmp);
			t.getLoc().removeToken(t);
			if(currentPlayer.hasCard() == true){
				
			}
			else{
				theModel.endTurn();
			}

		}else if (getAction().equals("outJail")){
			despMsg += "You used your Get out of jail free card.";
			currentPlayer.setHasCard(false);
		}else if (getAction().equals("GO")){
			Square sqtmp = (Square)(theModel.getBoard().getSquares().get("GO"));
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);
		}else if (getAction().equals("MAYFAIR")){
			Square sqtmp = theModel.getBoard().getSquares().get("MAYFAIR");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);


		}else if (getAction().equals("TRAFLGAR SQUARE")){
			Square sqtmp = theModel.getBoard().getSquares().get("TRAFLGAR SQUARE");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			

		}else if (getAction().equals("MARYLEBONE STATION")){
			Square sqtmp = theModel.getBoard().getSquares().get("MARYLEBONE STATION");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			



		}else if (getAction().equals("PALL MALL")){
			Square sqtmp = theModel.getBoard().getSquares().get("PALL MALL");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			


		}else if (getAction().equals("OLD KENT ROAD")){
			Square sqtmp = theModel.getBoard().getSquares().get("OLD KENT ROAD");
			Token t = theModel.getCurrPlayer().getToken();
			t.getLoc().removeToken(t);
			t.setLoc((Square) sqtmp);			
		}
		return despMsg;
	}
}