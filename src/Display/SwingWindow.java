package Display;

import java.util.ArrayList;

import javax.swing.JFrame;

public class SwingWindow implements Window{
	private JFrame f;
	private SwingPanel panel;
	
	public SwingWindow(){
		f = new JFrame();
		panel = new SwingPanel();
		f.add(panel);
		f.setBounds(100,50,500,300);
		f.setVisible(true);
	}


	@Override
	public void drawScene() {
		f.repaint();
	}


	@Override
	public ArrayList<Integer> getCurrentKeysPressed() {
		//todo: add a key listener to panel to scan for input
		ArrayList<Integer> keys = new ArrayList<Integer>();
		
		//hardcoded
		keys.add((int)'W');
		keys.add((int)'S');
		
		return keys;
	}

}
