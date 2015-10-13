package demo;

import Collision.CollisionBox;
import Collision.CollisionCircle;
import scripts.PlayerInput;
import scripts.cameraFollow;
import scripts.PlayerStatus;
import components.Animation;
import components.Animator;
import components.Sprite;
import components.SpriteSheet;
import framework.Game;
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
		
		//player sprite
		Sprite triangle = new Sprite("triangle.png", playerSize.intX(), playerSize.intY());
		add(triangle); //add component
		
		//offset position of rocket flames
		Vector smallRocketOffset = new Vector(-rocketSize.intX(),-rocketSize.intY()/2+playerSize.intY()/2);
		Vector bigRocketOffset = new Vector(-rocketSize.intX()*2,-rocketSize.intY()/2+playerSize.intY()/2);
		
		//rocket flame animation
		SpriteSheet s = new SpriteSheet("rocket.png", 32,32);
		Animation smallRocket = new Animation(s, rocketSize.intX(), rocketSize.intY(), smallRocketOffset,3);
		Animation bigRocket = new Animation(s, rocketSize.intX()*2, rocketSize.intY(), bigRocketOffset,3);
		Animator animator = new Animator();
		animator.addAnimation(smallRocket, "small");
		animator.addAnimation(bigRocket, "big");
		animator.setCurrentAnimation("small");
		add(animator);
	//	add(bigRocket);
		
		//collision boc
		add(new CollisionBox(0,0,playerSize.intX(), playerSize.intY(),false, true));
		add(new CollisionCircle(0,0,20,false,true));
		
		//Player stats i.e. time and lives
		add(new PlayerStatus());
		
		moveBy(playerStartPosition); //move player to a suitable starting position
		add(new PlayerInput(playerSize));//adding a script for input

		add(new cameraFollow(getID(), playerStartPosition));
	}
	
	
	
	
	

}
