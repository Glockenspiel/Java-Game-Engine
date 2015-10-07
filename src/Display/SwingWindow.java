package display;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import framework.Game;
import framework.Vector;

public class SwingWindow implements Window{
	//create and update the window
	//listen to input
	
	private JFrame f;
	private SwingPanel panel;
	private int w,h;
	
	public SwingWindow(int x, int y, int width, int height, boolean windowed, String windowName){
		f = new JFrame();
		f.setUndecorated(!windowed);
		f.setTitle(windowName);
		panel = new SwingPanel();
		f.add(panel);
		w=width;
		h=height;
		f.setBounds(x,y,width,height);
		
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

	//gets width of window
	@Override
	public int getWidth() {
		return w;
	}


	//gets height of window
	@Override
	public int getHeight() {
		return h;
	}

	//returns dimensions of window
	@Override
	public Vector getSize() {
		return new Vector(w,h);
	}
}
