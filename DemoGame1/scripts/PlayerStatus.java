package scripts;

import java.awt.Color;
import java.awt.image.BufferedImage;

import components.HUDItem;
import display.Drawer;
import framework.Event;
import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.Time;

public class PlayerStatus implements Script, HUDItem, Event {

	
	private int time = 0;
	private int health = 100;
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
		g.drawHUDText("HP: " + health, 15, 25, Color.BLACK);
	}
	
	@Override
	public void interuptThreads() {}

	@Override
	public void invokeEvent(String tag, int value) {
		if(tag.contains("Enemy")){
			health -= value;
			checkIsAlive();
		}
	}

	private void checkIsAlive() {
		if(health<0){
			health=0;
			//todo: end game
		}
	}
}
