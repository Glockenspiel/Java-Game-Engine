package demo;

import components.Sprite;

import framework.Game;
import framework.GameObject;
import framework.MathG;
import framework.Vector;

public class Enemy extends GameObject {
	
	private Vector moveSpeed = new Vector(-2,0);
	private Vector size = new Vector(32,32);

	public Enemy(String tag) {
		super(tag);
		
		add(new Sprite("box.png", 32, 32));
	}
	
	@Override
	public void update(){
		moveBy(moveSpeed);
	}

}
