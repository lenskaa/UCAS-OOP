package card;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// maintain a window
public class Window extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean restarted = false;

	JPanel panel = new JPanel();
	JPanel Cardpanel = new JPanel();

	JMenuItem start, exit, about;

	Dimension screensize;
	int width, height;

	public Window() {
		this.setTitle("Uno!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(930, 620);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);
		this.add(panel);
		// placeComponents(panel);
		SetMenu();
		this.setVisible(true);

		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screensize.getWidth();
		height = (int) screensize.getHeight();
	}

	/*
	 * private static void placeComponents(JPanel panel) {
	 * 
	 * panel.setLayout(null);
	 * 
	 * // JLabel userLabel = new JLabel(new ImageIcon("images/y0_.png")); Card
	 * userCard = new Card("y0", true); userCard.setLocation(100, 100); //
	 * userCard.setBounds(100, 100, 100, 155); panel.add(userCard);
	 * 
	 * Card userCard1 = new Card("g0", true); userCard1.setLocation(130, 100);
	 * panel.add(userCard1);
	 */

//		JButton loginButton = new JButton("A button");
//		loginButton.setBounds(0, 100, 100, 25);
//
//		// ......
//		panel.add(loginButton);
//	}

	public void addCard(Card card) {
		panel.add(card);
	}

	public void playCardPos(Card card) {
		Point play_positon = new Point();
		play_positon.x = width / 2 + 150;
		play_positon.y = height / 2 - 100;
		move(card, card.getLocation(), play_positon, 3);
	}

	// move a card from 'from' to 'to'
	public static void move(Card card, Point from, Point to, int t) {
		if (to.x != from.x) {
			double k = (1.0) * (to.y - from.y) / (to.x - from.x);
			double b = to.y - to.x * k;
			int flag = 0;
			if (from.x < to.x) {
				flag = 10;
			} else {
				flag = -10;
			}
			for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
				double y = k * i + b;
				System.out.println(y + "=" + k + "*" + i + "+" + b);
				card.setLocation(i, (int) y);
				try {
					Thread.sleep(t);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		// confirm the location
		card.setLocation(to);
	}

	// when the number of cards changed, reposition them
	public void rePosition(List<Card> list, int flag) {
		Collections.sort(list);
		Point new_p = new Point();
		if (flag == 0) {
			// bottom
			new_p.x = (width / 2) - (list.size() - 1) * 40 / 2 - 100 / 2;
			new_p.y = height - 250;
		} else if (flag == 1) {
			// left
			new_p.x = 100;
			new_p.y = (height / 2) - (list.size() - 1) * 30 / 2 - 155 / 2;
		} else if (flag == 2) {
			// top
			new_p.x = (width / 2) - (list.size() - 1) * 40 / 2 - 100 / 2;
			new_p.y = 50;
		} else if (flag == 3) {
			// right
			new_p.x = width - 200;
			new_p.y = (height / 2) - (list.size() - 1) * 30 / 2 - 155 / 2;
		}
		int len = list.size();
		int speed;
		if (flag == 0 || flag == 2) {
			speed = 5;
		} else {
			speed = 3;
		}
		for (int i = 0; i < len; i++) {
			Card card = list.get(i);
			Window.move(card, card.getLocation(), new_p, speed);
			panel.setComponentZOrder(card, 0);
			if (flag == 0 || flag == 2)
				new_p.x += 40;
			else
				new_p.y += 30;
		}
	}

	// Menu
	public void SetMenu() {
		JMenuBar jMenuBar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenu help = new JMenu("Help");

		start = new JMenuItem("New game");
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");

		start.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);

		game.add(start);
		game.add(exit);
		help.add(about);

		jMenuBar.add(game);
		jMenuBar.add(help);
		this.setJMenuBar(jMenuBar);

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start game");
				restarted = true;
				/*
				 * Judge judge = new Judge(); for (int i = 0; i <= 5; i++) { Card card =
				 * judge.drawCard(); addCard(card, i); repaint(); }
				 */

			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit");
			}
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("About");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// Win
	public void alertWin(int num) {
		JOptionPane.showMessageDialog(new JPanel(), "Player " + num + " wins the game!", "Uno",
				JOptionPane.DEFAULT_OPTION);
	}

}
