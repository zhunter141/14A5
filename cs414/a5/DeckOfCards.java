package cs414.a5;

import java.util.Collections;
import java.util.LinkedList;

public class DeckOfCards {
	
	private LinkedList<Card> cards;
	private Card firstCard;
	private int curCardCt;

	public DeckOfCards(Card[] card) 
	{
		cards = new LinkedList<Card>();
		
		for(int x = 0; x < card.length; x++)
		{
			cards.add(card[x]);
		}
		
		Collections.shuffle(cards);
		firstCard = cards.get(0);
		curCardCt = 0;
	}

	// Should cover both draw the top card and return it back in the bottom
	public Card drawCard() {
		cards.removeFirst();
		cards.addLast(firstCard);

		return firstCard;
	}
	
	//public void returnCard() {
	//	cards.addLast(firstCard);
	//}

	public void resetDeck() {
		// increment the card counter. If it reached the end, reset to zero
		curCardCt++;
		if (curCardCt == cards.size()) {
			curCardCt = 0;
		}
		firstCard = cards.get(curCardCt);
	}
}
