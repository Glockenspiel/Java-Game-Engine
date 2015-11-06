package framework;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import misc.Vector;

public class LauncherOptions {
	
	//display a dialog for setting the window size for the game
	public static Vector launch(){
		
		 String[] resolutions = { "854x480", "320x568" , "1024x768", "1280x800" ,"1366x768", "1920x1080", "Fullscreen" };

		 JFrame frame = new JFrame("Input Dialog Example 3");
		 String chosenRes = (String) JOptionPane.showInputDialog(frame, 
		      "Select window size",
		       "Resolution",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        resolutions, 
		        resolutions[0]);

		if(chosenRes==null)
			chosenRes=resolutions[0];
		else if(chosenRes.equalsIgnoreCase("Fullscreen")){
			//detect screen size
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			return new Vector((float)screenSize.getWidth(), (float)screenSize.getHeight());
		}
		
		String[] elements = chosenRes.split("x");
		Vector res = new Vector(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
		return res;
	}
	
}
