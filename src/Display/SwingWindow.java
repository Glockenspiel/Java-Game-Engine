package Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		
		f.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	Game.print().log("Closing game");
		    	System.exit(0);
		    }
		});
		
		f.setVisible(true);
		
	}


	@Override
	public void drawScene() {
		f.repaint();
	}
}
