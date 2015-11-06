package services;

import misc.Vector;

public interface Window {

	public void drawScene();
	public int getWidth();
	public int getHeight();
	public Vector getSize();
	public int getPreferredWidth();
	public int getPreferredHeight();
	public void setPreferredSize(int w, int h);
	public Vector getWindowScale();
	public void showCursor(boolean show);
	public void setCustomCursor(String filename);
}
