package demo;

import components.Animation;
import components.SpriteSheet;
import framework.GameObject;
import framework.Time;
import framework.Vector;

public class Explosion extends GameObject {

	public Explosion() {
		super("Explosion");
		
		SpriteSheet s = new SpriteSheet("explo.png", 70,70);
		add(new Animation(s,32,32,new Vector(0,0),3));
		delete((long)Time.FRAME_TIME*3*6);
	}

}
