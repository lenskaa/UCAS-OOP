package card;

import java.util.ArrayList;
import java.util.List;

public class Player {
	int cardNum = 0;
	List<Card> playerCardList = new ArrayList<Card>();

	public Player() {
		cardNum = 0;
	}

	public void initCard() {
		cardNum = 0;
		playerCardList.removeAll(playerCardList);
	}

	public void insertCard(Card card) {
		cardNum++;
		playerCardList.add(card);
	}

	public void openTouch() {
		for (int i = 0; i < playerCardList.size(); i++) {
			playerCardList.get(i).openTouch();
		}
	}

	public void closeTouch() {
		for (int i = 0; i < playerCardList.size(); i++) {
			playerCardList.get(i).closeTouch();
		}
	}

	public void removeCard(Card card) {
		card.closeTouch();
		cardNum--;
		playerCardList.remove(card);
	}

	public Card playCardDetect() {
		Card card = null;
		for (int i = 0; i < playerCardList.size(); i++) {
			if (playerCardList.get(i).clicked) {
				card = playerCardList.get(i);
				break;
			}
		}
		if (card != null)
			card.clicked = false;
		return card;
	}

	public void turnCardsToTop() {
		for (int i = 0; i < this.playerCardList.size(); i++) {
			this.playerCardList.get(i).turntoTop();
		}
	}

	public void turnCardsToBottom() {
		for (int i = 0; i < this.playerCardList.size(); i++) {
			this.playerCardList.get(i).turntoBottom();
			;
		}
	}

}
