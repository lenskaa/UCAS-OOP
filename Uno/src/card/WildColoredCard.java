package card;

import java.awt.Point;

public class WildColoredCard extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int func;

	public WildColoredCard(String name, boolean faceup, Point position) {
		super(name, faceup, position);
		super.type = 2;
		if (name.charAt(1) == 'd') {
			this.func = 0; // draw2
		} else if (name.charAt(1) == 'r') {
			this.func = 1; // reverse
		} else if (name.charAt(1) == 's') {
			this.func = 2; // skip
		}
	}

}
