package Display;

import java.awt.Color;
import java.awt.Image;

public interface Drawer {
	
	public void drawImage(Image img, int x, int y, int width, int height);

	public void drawLine(int x, int y, int xDirection, int yDirection, Color color);
	
	public void drawBox(int x, int y, int width, int height, Color color);
	
}
