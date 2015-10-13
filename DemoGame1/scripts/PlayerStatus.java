package scripts;

import java.awt.Color;
import java.awt.image.BufferedImage;

import components.HUDItem;
import display.Drawer;
import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.Time;

public class PlayerStatus implements Script, HUDItem {

	
	private int time = 0;
	private int lives = 3;
	private BufferedImage image;
			
	public PlayerStatus(){
		image = loaders.ImageLoader.load("hudImage.png");
	}
	
	@Override
	public void execute(GameObject obj) {
		time+=Time.FRAME_TIME;
	}

	
	@Override
	public void drawGUI(Drawer g) {
		if(image!=null)
			g.drawHUDImage(image, 0, 0, 100, 30);
		
		g.drawHUDText("Time: "+ time/1000, 15, 12, Color.BLACK);
		g.drawHUDText("Lives: " + lives, 15, 25, Color.BLACK);
	}
	
	@Override
	public void interuptThreads() {}
}
