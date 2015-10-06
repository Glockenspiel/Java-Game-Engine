package Display;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import framework.Game;

public class SwingWindow implements Window{
	//create and update the window
	//listen to input
	
	private JFrame f;
	private SwingPanel panel;
	
	public SwingWindow(int width, int height, boolean windowed, String windowName){
		f = new JFrame();
		f.setUndecorated(!windowed);
		f.setTitle(windowName);
		panel = new SwingPanel();
		f.add(panel);
		f.setBounds(100,50,width,height);
		
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
