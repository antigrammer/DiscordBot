import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import salil.resources.util.Random;
import sx.blah.discord.handle.obj.IUser;


public class Deck {

	private ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
	}
	public Deck(Deck d) {
		this.deck = d.deck;
	}
	public void add(Card c) {
		deck.add(c);
	}
	public void add(Card c, int n) {
		for(int i = 0; i < n; i++) {
			deck.add(c);
		}
	}
	public Card draw() {
		return deck.remove(0);
	}
	public boolean isEmpty() {
		return deck.size() == 0;
	}
	public void shuffle() {
		deck = Random.selectRandsNoRepeat(deck);
	}
	public int size() {
		return deck.size();
	}
	public ArrayList<Card> literal() {
		return deck;
	}
	public HashMap<IUser, Deck> deal(HashMap<IUser, Integer> cardCount){
		HashMap<IUser, Deck> cards = new HashMap<IUser, Deck>();
		Iterator<IUser> iterator = cardCount.keySet().iterator();
		while(iterator.hasNext()) {
			IUser i = iterator.next();
			int numCards = cardCount.get(i);
			Deck d = new Deck();
			for(int j = 0; j < numCards; j++) {
				if(!isEmpty())
					d.add(draw());
			}
			cards.put(i, d);
		}
		return cards;
	}
	public Card remove(int remove) {
		if (0 < remove && remove < deck.size())
			return deck.remove(remove);
		return null;
	}
	public Card remove(Card c) {
		for(int i = 0; i < deck.size(); i++) {
			if (deck.get(i).getName().equals(c.getName())) {
				deck.remove(i);
				return c;
			}
		}
		return null;
	}
	public Card removeLast() {
		if (deck.size() > 0)
			return remove(deck.get(deck.size()-1));
		return null;
	}
	
}