package card;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// manage cards
public abstract class Card extends JLabel implements MouseListener, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	int color;
	int type;
	int owner; // 0:Judge 1:Player 2:Discard
	boolean faceup;
	boolean moved = false;
	boolean enter_en = false;
	boolean click_en = false;
	boolean clicked = false;

	public Card(String name, boolean faceup, Point position) {
		this.name = name;
		this.owner = 0;

		if (name.charAt(0) == 'b') {
			this.color = 1;
		} else if (name.charAt(0) == 'g') {
			this.color = 2;
		} else if (name.charAt(0) == 'r') {
			this.color = 3;
		} else if (name.charAt(0) == 'y') {
			this.color = 4;
		} else {
			this.color = 0;
		}

		this.faceup = faceup;
		this.clicked = false;
		if (this.faceup)
			this.turntoTop();
		else {
			this.turntoBottom();
		}
		this.setSize(100, 155);
		this.setLocation(position);
		this.setVisible(true);
		this.addMouseListener(this);
	}

	public void turntoTop() {
		this.setIcon(new ImageIcon("images/" + name + ".png"));
		this.faceup = true;
	}

	public void turntoBottom() {
		this.setIcon(new ImageIcon("images/back.png"));
		this.faceup = false;
	}

	public void closeTouch() {
		enter_en = false;
		click_en = false;
	}

	public void openTouch() {
		enter_en = true;
	}

	public void openClick() {
		click_en = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (click_en && e.getClickCount() == 2) {
			// Play a card
			System.out.println("Currently click on:" + this.name);
			System.out.println("ko no card da!   " + this.name);
			clicked = true;
		} else if (this.owner == 0 && e.getClickCount() == 2) {
			// Draw a card
			System.out.println("Currently click on judge file");
			clicked = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (enter_en && !moved) {
			moved = true;
			click_en = true;
			System.out.println("mouse entered");
			Point from = this.getLocation();
			Window.move(this, from, new Point(from.x, from.y - 30), 10);
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
			Window.move(this, from, new Point(from.x, from.y + 30), 10);
		}
	}

	@Override
	public int compareTo(Object o) {
		Card card = (Card) o;
		if (this.name.charAt(0) > card.name.charAt(0)) {
			return 1;
		} else if (this.name.charAt(0) < card.name.charAt(0)) {
			return -1;
		} else if (this.type > card.type) {
			return 1;
		} else if (this.type < card.type) {
			return -1;
		} else if (this.name.charAt(1) > card.name.charAt(1)) {
			return 1;
		} else if (this.name.charAt(1) < card.name.charAt(1)) {
			return -1;
		} else if (this.name.length() >= card.name.length()) {
			return 1;
		} else if (this.name.length() < card.name.length()) {
			return -1;
		} else {
			return -1;
		}
	}

}