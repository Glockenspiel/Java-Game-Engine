package demo;

import prescripts.HurtAndDelete;
import misc.Vector;
import collision.CollisionBox;
import scripts.EnemyHit;
import scripts.MoveScript;
import components.RigidBody;
import components.Sprite;
import framework.GameObject;

public class Enemy extends GameObject {
	
	private Vector size = new Vector(32,64);
	private float maxSpeed = 15f;

	public Enemy(){}
	
	//constructor
	public Enemy(String tag) {
		super(tag);
		
		add(new Sprite("rock.png", size.intX(), size.intY()));
		add(new CollisionBox(0,0,size.intX(),size.intY()));
		//add(new EnemyHit(super.getID()));
		add(new HurtAndDelete("player", 10));
		RigidBody r = new RigidBody(100);
		r.setGravity(0);
		add(r);
		add(new MoveScript(getID(), maxSpeed));
	}
}
