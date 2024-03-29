package _03_IntroToStacks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.charset.Charset;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _02_TextUndoRedo implements KeyListener{
	 /* 
	 * Create a JFrame with a JPanel and a JLabel.
	 * 
	 * Every time a key is pressed, add that character to the JLabel. It should look like a basic text editor.
	 * 
	 * Make it so that every time the BACKSPACE key is pressed, the last character is erased from the JLabel.
	 * Save that deleted character onto a Stack of Characters.
	 * 
	 * Choose a key to be the Undo key. Make it so that when that key is pressed, the top Character is popped 
	 * off the Stack and added back to the JLabel.
	 * 
	 * */
	
	char c;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	String s;
	String n;
	Stack<Character> d = new Stack<Character>();
	public void run() {
		frame.add(panel);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(label);
		frame.pack();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		s = label.getText();
		if(e.getExtendedKeyCode()!=KeyEvent.VK_BACK_SPACE && e.getExtendedKeyCode()!=KeyEvent.VK_ENTER) {
		c = e.getKeyChar();
		label.setText(s + c);
		}
		
		
		if(e.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE) {
			d.push(s.charAt(s.length()-1));
			n = s.substring(0,s.length()-1);
			label.setText(n);
		}
		if(e.getExtendedKeyCode()==KeyEvent.VK_ENTER) {
			label.setText(s+d.pop());
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
	
}
