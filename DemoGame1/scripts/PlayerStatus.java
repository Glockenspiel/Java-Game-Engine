package scripts;

import java.awt.Color;
import java.awt.image.BufferedImage;

import misc.Time;
import components.HUDItem;
import display.Drawer;
import framework.EventListener;
import framework.GameObject;

public class PlayerStatus implements HUDItem, EventListener {

	
	private int time = 0;
	private int health = 100;
	private int money = 0;
	private BufferedImage image;
	
	//constructor
	public PlayerStatus(){
		image = loaders.ImageLoader.load("hudImage.png");
	}
	
	public int getHP(){
		return health;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	//update time
	@Override
	public void execute(GameObject obj) {
		time+=Time.FRAME_TIME;
	}

	//draw the HUD for player status
	@Override
	public void drawGUI(Drawer g) {
		if(image!=null)
			g.drawHUDImage(image, 0, 0, 100, 55);
		
		g.drawHUDText("Time: "+ time/1000, 15, 15, Color.BLACK);
		g.drawHUDText("HP: " + health, 15, 30, Color.BLACK);
		g.drawHUDText("Money: " + money, 15, 45, Color.BLACK);
	}

	@Override
	public void notify(String tag, int value) {
		if(tag.contains("enemy_hit")){
			health -= value;
			checkIsAlive();
		}
		
		else if(tag.equalsIgnoreCase("Coin_collected")){
			money+=value;
		}
	}

	//check if the player health falls below zero
	private void checkIsAlive() {
		if(health<0){
			health=0;
			//todo: end game
		}
	}
}
