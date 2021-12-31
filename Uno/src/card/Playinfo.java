package card;

public class Playinfo {
	boolean canPlay;
	int cardPlus;
	int playerNumber;
	int playOrder;
	int cardColor;
	int cardNumber;

	public Playinfo() {
		this.canPlay = true;
		this.cardPlus = 1;
		this.playerNumber = 0;
		this.playOrder = 1;
		this.cardColor = 1;
		this.cardNumber = 0;
	}

	public void turnPlayer() {
		this.playerNumber = this.playerNumber + this.playOrder;
		if (this.playerNumber == 4)
			this.playerNumber = 0;
		if (this.playerNumber == -1)
			this.playerNumber = 3;
	}
}
