package card;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class ColorCard extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorCard(String name, boolean faceup, Point position) {
		super(name, faceup, position);
		this.setSize(50, 78);
		click_en = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Currently a click on:" + this.name);
		if (click_en) {
			clicked = true;
			Point from = this.getLocation();
			Window.move(this, from, new Point(from.x, from.y + 10), 10);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (enter_en && !moved) {
			moved = true;
			click_en = true;
			System.out.println("mouse entered");
			Point from = this.getLocation();
			Window.move(this, from, new Point(from.x, from.y - 10), 10);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (enter_en && moved) {
			moved = false;
			click_en = false;
			System.out.println("mouse exited");
			Point from = this.getLocation();
			Window.move(this, from, new Point(from.x, from.y + 10), 10);
		}

	}
}
