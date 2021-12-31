package card;

public class Game {

	Window window = new Window();

	Judge judge = new Judge(window.width, window.height);

	Player[] player = new Player[4];

	public static void main(String args[]) {
		new Game();
	}

	public Game() {

		for (int i = 0; i < 4; i++) {
			player[i] = new Player();
		}
		// window.addCard(judge.drawCard());
		// window.repaint();
		Card card;
		int playernum;

		while (true) {
			if (window.restarted) {
				window.restarted = false;
				initGame();
				dealCard();
				randomStart();
				window.repaint();
				System.out.println("init:");
				while (true) {
					playernum = judge.getPlayer();
					if (judge.getCanPlay())
						player[playernum].openTouch();

					// If player plays a card
					card = player[playernum].playCardDetect();
					if (judge.checkCard(card)) {
						System.out.println("playcard:" + card.name);
						player[playernum].removeCard(card);
						judge.discardCard(card);
						player[playernum].closeTouch();
						window.playCardPos(card);
						window.rePosition(player[playernum].playerCardList, playernum);
						judge.wildCardPlay(card);
					}

					if (judge.winCheck(player[playernum].cardNum)) {
						window.alertWin(playernum);
						break;
					}

					// If play draws a card
					card = judge.drawCardDetect();
					if (judge.checkCard(card)) {
						System.out.println("draw cards");
						player[playernum].closeTouch();
						for (int i = 0; i < judge.getCardPlus(); i++) {
							Card card_draw = judge.drawCardFromPile();
							player[playernum].insertCard(card_draw);
						}
						window.rePosition(player[playernum].playerCardList, playernum);
						player[playernum].turnCardsToTop();
						judge.resetCardPlus();
					}
				}
			} else {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void initGame() {
		judge.initCard(window.width, window.height);
		for (int i = 0; i < 4; i++) {
			player[i].initCard();
		}
		for (int i = 0; i < judge.judgeCardList.size(); i++) {
			window.panel.add(judge.judgeCardList.get(i));
			window.panel.setComponentZOrder(judge.judgeCardList.get(i), i);
		}
		for (int i = 0; i < judge.colorCardList.size(); i++) {
			window.panel.add(judge.colorCardList.get(i));
			window.panel.setComponentZOrder(judge.colorCardList.get(i), i);
		}
		window.repaint();
	}

	public void dealCard() {
		Card card;
		for (int i = 0; i < 4; i++) {
			// draw cards from judge
			for (int j = 0; j < 1; j++) {
				card = judge.drawCardFromPile();
				player[i].insertCard(card);
			}
			// reposition the cards
			window.rePosition(player[i].playerCardList, i);
			// turn over the cards
			player[i].turnCardsToTop();
		}

	}

	public void randomStart() {
		Card card = judge.randomStartDraw();
		window.playCardPos(card);
		card.turntoTop();
	}
}
