package demo;

import collision.CollisionBox;
import scripts.CoinCollected;
import scripts.MoveScript;
import components.RigidBody;
import components.Sprite;
import framework.GameObject;
import framework.MathG;
import framework.Vector;

//a coin
public class Coin extends GameObject {
	
	public Coin(String tag){
		super(tag);
		
		int w=32,h=32;
		RigidBody r = new RigidBody(20);
		r.setGravity(0);
		add(r);
		add(new CollisionBox(0,0,w,h,true));
		add(new MoveScript(getID(), 25f));
		add(new Sprite("coins.png", w,h));
		add(new CoinCollected(getID(),3));
		moveBy(new Vector(0, MathG.randomNumber(0, 300)));
		delete(5000);
	}
	
	
}
