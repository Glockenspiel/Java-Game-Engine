package display;

import java.awt.Cursor;
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
	private static int preferredW=-1, preferredH=-1;
	private Cursor cursor = Cursor.getDefaultCursor();
	
	//constructor
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

	//calls the repaint method
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

	//returns the preferred width of the window
	@Override
	public int getPreferredWidth() {
		return preferredW;
	}


	//returns the preferred height of the window
	@Override
	public int getPreferredHeight() {
		return preferredH;
	}


	//returns the scale of the window size to the preferred window size
	@Override
	public Vector getWindowScale() {
		return new Vector((float)w/(float)preferredW, (float)h/(float)preferredH);
	}

	//set the preffered size
	@Override
	public void setPreferredSize(int w, int h) {
		preferredW=w;
		preferredH=h;
	}

	//show or hide the cursor
	@Override
	public void showCursor(boolean show) {
		if(show==false)
			f.getContentPane().setCursor(CursorCreator.getBlankCursor());
		else{
			f.getContentPane().setCursor(cursor);
		}
	}

	//set a custom cursor
	@Override
	public void setCustomCursor(String filename) {
		cursor = CursorCreator.getCustomCursor("Resources/Images/"+filename);
		f.getContentPane().setCursor(cursor);
	}
}
