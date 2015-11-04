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
	private final static String defaultTag = "Player";

	public Player() {
		super(defaultTag);
		setDrawLayer(10);
		
		createPlayer();
	}
	
	//initalise the player
	private void createPlayer() {
		//dimensions to display images
		Vector playerSize = new Vector(100,32);
		Vector rocketSize = new Vector(32,32);
		
		Vector startPos = new Vector(50, Game.getServices().getWindow().getPreferredHeight()/2);
		
		setIsGlobal(true);
		
		playerStartPosition = new Vector(startPos.intX(),startPos.intY()-playerSize.intY()/2);
		
		
		
		//offset position of rocket flames
		int offX = 7;
		Vector smallRocketOffset = new Vector(0+offX,-rocketSize.intY()/2+playerSize.intY()/2);
		Vector bigRocketOffset = new Vector(-rocketSize.intX()+offX,-rocketSize.intY()/2+playerSize.intY()/2);
		
		//rocket flame animation
		SpriteSheet s = new SpriteSheet("rocket.png", 32,32);
		Animation smallRocket = new Animation(s, rocketSize.intX(), rocketSize.intY(), smallRocketOffset,3);
		Animation bigRocket = new Animation(s, rocketSize.intX()*2, rocketSize.intY(), bigRocketOffset,3);
		Animator animator = new Animator();
		animator.addAnimation(smallRocket, "small");
		animator.addAnimation(bigRocket, "big");
		animator.setCurrentAnimation("small");
		add(animator);
		
		//player sprite
		Sprite ship = new Sprite("spaceship.png", playerSize.intX(), playerSize.intY());
		add(ship); //add component
		
		
		//collision box
		add(new CollisionBox(0,0,playerSize.intX(), playerSize.intY(),false));
		//add(new CollisionCircle(0,0,20,false,true));
		
		//Player stats i.e. time and lives
		add(new PlayerStatus());
		
		moveBy(playerStartPosition); //move player to a suitable starting position
		add(new PlayerInput(playerSize));//adding a script for input

		add(new cameraFollow(getID(), playerStartPosition));
	}
	
	public static String getDefaultTag(){
		return defaultTag;
	}
	
	
	

}
