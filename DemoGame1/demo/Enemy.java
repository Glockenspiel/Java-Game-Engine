package demo;

import Collision.CollisionBox;
import components.RigidBody;
import components.Sprite;
import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.MathG;
import framework.Time;
import framework.Vector;

public class Enemy extends GameObject {
	
	private Vector moveSpeed = new Vector(-10,0);
	private Vector size = new Vector(32,64);
	private float maxSpeed = 15f;

	public Enemy(String tag) {
		super(tag);
		
		add(new Sprite("rock.png", size.intX(), size.intY()));
		add(new CollisionBox(0,0,size.intX(),size.intY(),true,true));
		RigidBody r = new RigidBody(100);
		r.setGravity(0);
		add(r);
	}
	
	@Override
	public void update(){
		super.update();
		Component c = super.getComponentByType("RigidBody");
		if(c instanceof RigidBody){
			RigidBody body =((RigidBody) c);
			if(body.getVelocityDistance()<maxSpeed)
				body.addForce(moveSpeed.getDeltaVector());
		}
	}

}
