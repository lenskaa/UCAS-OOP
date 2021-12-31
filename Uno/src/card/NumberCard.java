package card;

import java.awt.Point;

public class NumberCard extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int number;

	public NumberCard(String name, boolean faceup, Point position) {
		super(name, faceup, position);
		super.type = 1;
		this.number = name.charAt(1) - '0';
	}

}
