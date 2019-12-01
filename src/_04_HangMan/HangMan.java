package _04_HangMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HangMan implements KeyListener {
	JLabel label = new JLabel();
	JFrame frame = new JFrame();
	String current;
	String labelText;
	Stack<String> words = new Stack<String>();
	int lives = 10;
	ArrayList<Character> guessed = new ArrayList<Character>();

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (current.contains("" + e.getKeyChar()) && lives > 0) {
			for (int i = 0; i < current.length(); i++) {
				if (current.charAt(i) == e.getKeyChar()) {
					labelText = labelText.substring(0, i) + e.getKeyChar()
							+ labelText.substring(i + 1, labelText.length());
					guessed.add(e.getKeyChar());
				}
			}
		} else if (!labelText.contains("" + e) && lives > 0 && !guessed.contains(e.getKeyChar())) {
			lives--;
			guessed.add(e.getKeyChar());
		} 
		label.setText(labelText + "   Lives: " + lives);
		if (labelText.equals(current) || lives < 1) {
			if (lives >= 1) {
				JOptionPane.showMessageDialog(null, "Congrats");
				if (words.size() > 0) {
					labelText = "";

					current = words.pop();
					for (int i = 0; i < current.length(); i++) {
						labelText = labelText + "_";
						label.setText(labelText);
						frame.pack();
					}
					lives=10;
				} else {
					int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Good Job",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						frame.dispose();
						lives=10;
						run();
					} else {
						frame.dispose();
					}
				}
			} else {
				
				int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game over",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					frame.dispose();
					lives=10;
					run();
				} else {
					frame.dispose();
				}
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void run() {
		frame.add(label);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String maxNumString = JOptionPane.showInputDialog("How many words would you like to guess?");
		int maxNum = Integer.parseInt(maxNumString);
		String pending;
		for (int i = 0; i < maxNum; i++) {
			pending = Utilities.readRandomLineFromFile("dictionary.txt");
			if (words.contains(pending))
				i--;
			else
				words.push(pending);
		}
		labelText = "";

		current = words.pop();
		for (int i = 0; i < current.length(); i++) {
			labelText = labelText + "_";
			label.setText(labelText);
			frame.pack();
		}
	}

}
