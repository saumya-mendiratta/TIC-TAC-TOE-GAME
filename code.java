package game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe extends JFrame implements ActionListener {

	//cmt
	public static int BOARD_SIZE = 3;

	public enum Gamestatus {

		incomplete, xwins, zwins, tie;
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossturn = true;

	public TicTacToe() {

		super.setTitle("TicTacToe");
		super.setSize(800, 800);
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);

		Font font = new Font("Comic Sans", 1, 150);

		for (int row = 0; row < BOARD_SIZE; row++) {

			for (int col = 0; col < BOARD_SIZE; col++) {

				JButton button = new JButton("");
				buttons[row][col] = button;
				button.setFont(font);
				super.add(button);
				button.addActionListener(this);

			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clickedbutton = (JButton) e.getSource();
		makemove(clickedbutton);
		Gamestatus gs = this.getGamestatus();

		if (gs == Gamestatus.incomplete)
			return;

		declarewinner(gs);

		int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart");

		if (choice == JOptionPane.YES_OPTION) {

			for (int row = 0; row < BOARD_SIZE; row++) {

				for (int col = 0; col < BOARD_SIZE; col++) {

					buttons[row][col].setText("");	
				}
			}
			crossturn = true;
		}else {
			super.dispose();
		}
	}

	private void makemove(JButton clickedbutton) {

		String btntext = clickedbutton.getText();

		if (btntext.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid click");

		} else {

			if (crossturn)
				clickedbutton.setText("X");
			else
				clickedbutton.setText("0");

		}

		crossturn = !crossturn;
	}

	private Gamestatus getGamestatus() {

		String text1 = "", text2 = "";
		int row = 0, col = 0;

		// text inside rows
		row = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {

				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();

				if (!text1.equals(text2) || text1.length() == 0)
					break;

				col++;

			}

			if (col == BOARD_SIZE - 1) {

				if (text1.equals("X"))
					return Gamestatus.xwins;
				else
					return Gamestatus.zwins;
			}

			row++;
		}

		// text inside columns
		col = 0;
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {

				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();

				if (!text1.equals(text2) || text1.length() == 0)
					break;

				row++;

			}

			if (row == BOARD_SIZE - 1) {

				if (text1.equals("X"))
					return Gamestatus.xwins;
				else
					return Gamestatus.zwins;
			}

			col++;
		}

		// text in diagonal1
		row = 0;
		col = 0;
		while (row < BOARD_SIZE - 1) {

			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();

			if (!text1.equals(text2) || text1.length() == 0)
				break;

			row++;
			col++;
		}

		if (row == BOARD_SIZE - 1) {

			if (text1.equals("X"))
				return Gamestatus.xwins;
			else
				return Gamestatus.zwins;

		}

		// text in diagonal2
		row = BOARD_SIZE - 1;
		col = 0;
		while (row > 0) {

			text1 = buttons[row][col].getText();
			text2 = buttons[row - 1][col + 1].getText();

			if (!text1.equals(text2) || text1.length() == 0)
				break;

			row--;
			col++;
		}

		if (row == 0) {

			if (text1.equals("X"))
				return Gamestatus.xwins;
			else
				return Gamestatus.zwins;

		}

		String txt = "";
		for (row = 0; row < BOARD_SIZE; row++) {
			for (col = 0; col < BOARD_SIZE; col++) {

				txt = buttons[row][col].getText();
				if (txt.length() == 0)
					return Gamestatus.incomplete;
			}
		}

		return Gamestatus.tie;
	}

	private void declarewinner(Gamestatus gs) {

		if (gs == Gamestatus.xwins)
			JOptionPane.showMessageDialog(this, "X wins the game ");
		else if (gs == Gamestatus.zwins)
			JOptionPane.showMessageDialog(this, "0 wins the game ");
		else
			JOptionPane.showMessageDialog(this, "It is a TIE ");

	}

}
