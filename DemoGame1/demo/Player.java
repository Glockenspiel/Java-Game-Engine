package demo;

import scripts.PlayerInput;
import scripts.cameraFollow;
import scripts.PlayerStatus;

import components.Animation;
import components.Sprite;
import components.SpriteSheet;

import framework.GameObject;
import framework.Vector;

public class Player extends GameObject {
	
	private Vector playerStartPosition;

	public Player(String tag, Vector startPos) {
		super(tag);
		
		createPlayer(startPos);
	}

	private void createPlayer(Vector startPos) {
		//dimensions to display images
		Vector playerSize = new Vector(64,64);
		Vector rocketSize = new Vector(32,32);
		
		playerStartPosition = new Vector(startPos.intX(),startPos.intY()-playerSize.intY()/2);
		
		
		//offset position of rocket flames
		Vector rocketOffset = new Vector(-rocketSize.intX(),-rocketSize.intY()/2+playerSize.intY()/2);
		
		Sprite triangle = new Sprite("triangle.png", playerSize.intX(), playerSize.intY());
		add(triangle); //add component
		
		
		//rocket flame animation
		SpriteSheet s = new SpriteSheet("rocket.png", 32,32);
		add(new Animation(s, rocketSize.intX(), rocketSize.intY(), rocketOffset,3));
		
		add(new PlayerStatus());
		
		moveBy(playerStartPosition); //move player to a suitable starting position
		add(new PlayerInput(playerSize));//adding a script for input

		add(new cameraFollow(getID(), playerStartPosition));
	}
	
	
	
	
	

}
