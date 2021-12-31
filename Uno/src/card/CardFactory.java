package card;

import java.awt.Point;

public class CardFactory {
	int window_width, window_height;
	int init_x_position, init_y_position;
	int init_position_bias;
	int init_position_scale;
	int init_color_position_bias;
	int init_color_position_scale;

	public CardFactory() {
		init_position_bias = 0;
		init_position_scale = 20;
		init_color_position_bias = 0;
		init_color_position_scale = 55;
	}

	public Card getCard(String cardtype, String name) {

		Card card;
		if ("NumberCard".equals(cardtype)) {
			card = new NumberCard(name, false, getInitPosition(0));
			return card;
		} else if ("WildColoredCard".equals(cardtype)) {
			card = new WildColoredCard(name, false, getInitPosition(0));
			return card;
		} else if ("WildBlackCard".equals(cardtype)) {
			card = new WildBlackCard(name, false, getInitPosition(0));
			return card;
		} else if ("ColorCard".equals(cardtype)) {
			card = new ColorCard(name, true, getInitPosition(1));
			return card;
		} else {
			return null;
		}

	}

	public Point getInitPosition(int postype) {
		Point pos = new Point();
		if (postype == 0) {
			pos.x = init_x_position + init_position_bias * init_position_scale;
			pos.y = init_y_position;
			init_position_bias++;
			if (init_position_bias > 3)
				init_position_bias = 0;
		} else if (postype == 1) {
			pos.x = init_x_position - 25 + init_color_position_bias * init_color_position_scale;
			pos.y = init_y_position;
			init_color_position_bias++;
		}

		return pos;
	}

	public void setSize(int width, int height) {
		window_width = width;
		window_height = height;
		init_x_position = width / 2 - 300;
		init_y_position = height / 2 - 100;
	}
}
