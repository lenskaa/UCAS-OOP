package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Judge {
	int cardNum;
	Playinfo playinfo = new Playinfo();
	CardFactory cardFactory = new CardFactory();
	List<Card> colorCardList = new ArrayList<Card>();
	List<Card> judgeCardList = new ArrayList<Card>();
	List<Card> discardCardList = new ArrayList<Card>();

	public Judge(int width, int height) {
		initCard(width, height);
	}

	public void initCard(int width, int height) {
		cardFactory.setSize(width, height);

		// initialize cards
		cardNum = 108;
		judgeCardList.removeAll(judgeCardList);
		discardCardList.removeAll(discardCardList);
		colorCardList.removeAll(colorCardList);

		this.initNumberCards();
		this.initColoredWildCards();
		this.initBlackWildCards();
		this.initColorCards();

	}

	public void initNumberCards() {
		// cards with numbers
		Card card;
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < 2; j++) {
				card = cardFactory.getCard("NumberCard", "b" + i);
				judgeCardList.add(card);

				card = cardFactory.getCard("NumberCard", "g" + i);
				judgeCardList.add(card);

				card = cardFactory.getCard("NumberCard", "r" + i);
				judgeCardList.add(card);

				card = cardFactory.getCard("NumberCard", "y" + i);
				judgeCardList.add(card);

				if (i == 0)
					break;
			}
		}
	}

	public void initColoredWildCards() {
		// draw2, reverse and skip cards
		Card card;
		for (int i = 0; i < 2; i++) {
			card = cardFactory.getCard("WildColoredCard", "bdraw2");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "gdraw2");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "rdraw2");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "ydraw2");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "breverse");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "greverse");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "rreverse");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "yreverse");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "bskip");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "gskip");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "rskip");
			judgeCardList.add(card);

			card = cardFactory.getCard("WildColoredCard", "yskip");
			judgeCardList.add(card);
		}
	}

	public void initBlackWildCards() {
		// wild cards
		Card card;
		for (int i = 0; i < 4; i++) {
			card = cardFactory.getCard("WildBlackCard", "zwild");
			judgeCardList.add(card);
		}
		for (int i = 0; i < 4; i++) {
			card = cardFactory.getCard("WildBlackCard", "zwild4");
			judgeCardList.add(card);
		}
	}

	public void initColorCards() {
		// color cards
		Card card;
		card = cardFactory.getCard("ColorCard", "red");
		colorCardList.add(card);
		card = cardFactory.getCard("ColorCard", "yellow");
		colorCardList.add(card);
		card = cardFactory.getCard("ColorCard", "blue");
		colorCardList.add(card);
		card = cardFactory.getCard("ColorCard", "green");
		colorCardList.add(card);
	}

	public Card drawCardFromPile() {
		Card card;
		Random random = new Random();
		int a = random.nextInt(cardNum);
		card = judgeCardList.get(a);
		card.owner = 1;
		judgeCardList.remove(a);
		cardNum--;
		return card;
	}

	public void addCardToPile(Card card) {
		judgeCardList.add(card);
	}

	public Card drawCardDetect() {
		Card card = null;
		for (int i = 0; i < judgeCardList.size(); i++) {
			if (judgeCardList.get(i).clicked) {
				card = judgeCardList.get(i);
				break;
			}
		}
		if (card != null)
			card.clicked = false;
		return card;
	}

	public boolean checkCard(Card card) {
		if (card == null)
			return false;
		if (card.owner == 0) {
			playinfo.canPlay = true;
		} else if (card.owner == 1) {
			if (card.type == 1) {
				NumberCard numbercard = (NumberCard) card;
				if (numbercard.number == playinfo.cardNumber || numbercard.color == playinfo.cardColor) {
					playinfo.cardColor = numbercard.color;
					playinfo.cardNumber = numbercard.number;
				} else {
					return false;
				}
			} else if (card.type == 2) {
				WildColoredCard wildcoloredcard = (WildColoredCard) card;
				if (wildcoloredcard.color == playinfo.cardColor) {
					playinfo.cardColor = wildcoloredcard.color;
					playinfo.cardNumber = -1;
					if (wildcoloredcard.func == 0) {
						// draw2
						playinfo.cardPlus = 2;
						playinfo.canPlay = false;
					} else if (wildcoloredcard.func == 1) {
						// reverse
						playinfo.playOrder = -playinfo.playOrder;
					} else if (wildcoloredcard.func == 2) {
						// skip
						playinfo.cardPlus = 1;
						playinfo.canPlay = false;
					}
				} else {
					return false;
				}
			} else if (card.type == 0) {
				WildBlackCard wildblackcard = (WildBlackCard) card;
				playinfo.cardNumber = -1;
				if (wildblackcard.func == 0) {
					// wild
					playinfo.cardColor = 0;
				} else if (wildblackcard.func == 1) {
					// wild4
					playinfo.cardColor = 0;
					playinfo.cardPlus = 4;
					playinfo.canPlay = false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
		playinfo.turnPlayer();
		return true;
	}

	public void wildCardPlay(Card card) {
		if (card.type == 0) {
			this.openColor();
			this.setColor(this.colorCardDetect());
			this.closeColor();
		}
	}

	public int colorCardDetect() {
		Card card = null;
		while (true) {
			for (int i = 0; i < colorCardList.size(); i++) {
				if (colorCardList.get(i).clicked) {
					card = colorCardList.get(i);
					break;
				}
			}
			if (card != null)
				break;
		}
		if (card != null)
			card.clicked = false;
		return card.color;
	}

	public int getPlayer() {
		return playinfo.playerNumber;
	}

	public boolean getCanPlay() {
		return playinfo.canPlay;
	}

	public int getCardPlus() {
		return playinfo.cardPlus;
	}

	public void resetCardPlus() {
		playinfo.cardPlus = 1;
	}

	public void setColor(int color) {
		playinfo.cardColor = color;
	}

	public void discardCard(Card card) {
		this.discardCardList.add(card);
	}

	public void openColor() {
		for (int i = 0; i < this.colorCardList.size(); i++) {
			this.colorCardList.get(i).openTouch();
			this.colorCardList.get(i).openClick();
		}
	}

	public void closeColor() {
		for (int i = 0; i < this.colorCardList.size(); i++) {
			this.colorCardList.get(i).closeTouch();
		}
	}

	public Card randomStartDraw() {
		Card card = drawCardFromPile();
		while (card.type != 1) {
			this.addCardToPile(card);
			card = drawCardFromPile();
		}
		playinfo.cardColor = ((NumberCard) card).color;
		playinfo.cardNumber = ((NumberCard) card).number;
		return card;
	}

	public boolean winCheck(int num) {
		if (num == 0)
			return true;
		else
			return false;
	}
}
