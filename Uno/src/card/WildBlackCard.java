package card;

import java.awt.Point;

public class WildBlackCard extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int func;

	public WildBlackCard(String name, boolean faceup, Point position) {
		super(name, faceup, position);
		super.type = 0;
		if (name.length() == 5) {
			this.func = 0;// wild
		} else {
			this.func = 1;// wild4
		}
	}
}