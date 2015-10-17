package display;

import framework.Vector;

public interface Window {

	public void drawScene();
	public int getWidth();
	public int getHeight();
	public Vector getSize();
	public int getPreferredWidth();
	public int getPreferredHeight();
	public void setPreferredSize(int w, int h);
	public Vector getWindowScale();
}
