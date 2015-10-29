package demo;

import components.Animation;
import components.SpriteSheet;
import framework.GameObject;
import framework.Time;
import framework.Vector;

public class Explosion extends GameObject {

	private boolean once=true;
	
	//create a default explosion of size 32x32
	public Explosion() {
		super("Explosion");
		init(0,0,32,32);
	}
	
	//custom explosion construcotr
	public Explosion(int offsetX, int offsetY, int height, int width) {
		super("Explosion");
		init(offsetX, offsetY, height, width);
	}
	
	//initialise the explosion
	private void init(int x, int y, int w, int h){
		SpriteSheet s = new SpriteSheet("explo.png", 70,70);
		add(new Animation(s,w,h,new Vector(x,y),3));
	}
	
	//update the Explosition
	@Override
	public void update(){
		super.update();
		
		//call delete once
		if(once){
			once=false;
			delete((long)Time.FRAME_TIME*3*6);
		}
	}

}
