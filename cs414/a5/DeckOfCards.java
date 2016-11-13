package cs414.a5;

import java.util.Collections;
import java.util.LinkedList;

public class DeckOfCards {
	
	LinkedList<Card> cards;
	private Card firstCard;

	public DeckOfCards(Card[] card) 
	{
		cards = new LinkedList<Card>();
		
		for(int x = 0; x < card.length; x++)
		{
			cards.add(card[x]);
		}
		
		Collections.shuffle(cards);
	}

	// Should cover both draw the top card and return it back in the bottom
	public Card drawCard() {
		firstCard = cards.getFirst();
		cards.removeFirst();
		if(firstCard.getDescription().equals("Get out of Jail free")){
			//do nothing 
		}
		else{cards.addLast(firstCard);}
		
		return firstCard;
	}
	

}
