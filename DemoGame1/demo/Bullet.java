package demo;

import misc.Vector;
import collision.CollisionBox;
import scripts.BulletScript;
import components.Sprite;
import framework.GameObject;

public class Bullet extends GameObject {
	
	private Vector bulletSize = new Vector(16,8);

	public Bullet(){}
	
	//a bullet
	public Bullet(String tag) {
		super(tag);

		//add components and scripts
		add(new Sprite("laser.png", bulletSize.intX(), bulletSize.intY()));
		add(new CollisionBox(0,0,bulletSize.intX(), bulletSize.intY()));
		add(new BulletScript(10,0, getID()));
	}
	
	public Vector getSize(){
		return bulletSize;
	}

}
