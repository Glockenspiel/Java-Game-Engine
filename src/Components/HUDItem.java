package components;

import display.Drawer;
import framework.Script;

public interface HUDItem extends Script{
	//draw to the HUD rather than in the game world
	public void drawGUI(Drawer drawer);
}
