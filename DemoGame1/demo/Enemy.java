package demo;

import misc.MathG;
import misc.Time;
import misc.Vector;
import collision.CollisionBox;
import collision.CollisionCircle;
import scripts.EnemyHit;
import scripts.MoveScript;
import components.RigidBody;
import components.Sprite;
import framework.Component;
import framework.Game;
import framework.GameObject;

public class Enemy extends GameObject {
	
	private Vector moveSpeed = new Vector(-10,0);
	private Vector size = new Vector(32,64);
	private float maxSpeed = 15f;

	//constructor
	public Enemy(String tag) {
		super(tag);
		
		add(new Sprite("rock.png", size.intX(), size.intY()));
		add(new CollisionBox(0,0,size.intX(),size.intY()));
		add(new EnemyHit(super.getID()));
		RigidBody r = new RigidBody(100);
		r.setGravity(0);
		add(r);
		add(new MoveScript(getID(), maxSpeed));
	}
}
