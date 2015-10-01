package Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import framework.Game;

public class SwingWindow implements Window{
	//create and update the window
	//listen to input
	
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
}
