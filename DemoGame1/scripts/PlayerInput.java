package scripts;

import java.awt.event.KeyEvent;

import Collision.CollisionBox;
import components.Animator;
import components.Sprite;
import demo.Bullet;
import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.Vector;

public class PlayerInput implements Script {

	private int speed=3;
	private boolean once=true;
	private Vector playerSize;
	
	public PlayerInput(Vector playerSize){
		this.playerSize=playerSize;
	}
	
	//take player input and do commands with it
	@Override
	public void execute(GameObject obj) {
		//move player
		Vector direction = Game.getInput().getDirectionInput();
		Vector displacement = Vector.multiply(direction, new Vector(speed,speed));
		obj.moveBy(displacement.getDeltaVector());
		//obj.moveBy(Vector.multiply(displacement, new Vector(-1,-1)));
	//	Game.getCamera().moveBy(displacement);
		

		//shoot
		if(Game.getInput().isKeyPressed('P')){
			shoot(obj.getPosition());
		}
		
		//deleting a GameObject with keyboard input
		if(once && Game.getInput().isKeyDown('Q')){
			Game.deleteObjByTag("map");
			once=false;
		}
		
		//enable boost
		char boostKey = (char)KeyEvent.VK_SPACE;
		if(Game.getInput().isKeyDown(boostKey)){
			speed = 20;
		}
		else{
			speed = 10;
		}
		
		//toggle debug drawing
		if(Game.getInput().isKeyPressed('L')){
			Game.enableDebugDraw(!Game.isDrawingDebug());
		}
		
		if(Game.getInput().isKeyPressed('B')){
			Game.getSaving().saveState();
		}
		if(Game.getInput().isKeyPressed('N')){
			Game.load();
		}
		
		if(Game.getInput().isKeyPressed('H')){
			boolean flag=true;
			while(flag){
				//if(Game.getInput().isKeyReleased('H'))
					flag=false;
			}
		}
		
		//display animation for boost
		Animator a = (Animator) obj.getComponentByType(Animator.class);
		if(a!=null){
			if(Game.getInput().isKeyDown(boostKey) && a.getCurrentState()!="big"){
					a.setCurrentAnimation("big");
			}
			else if(Game.getInput().isKeyUp(boostKey)  && a.getCurrentState()!="small"){
				a.setCurrentAnimation("small");
			}
		}
	}

	//spawn bullet object
	private void shoot(Vector position) {
		Bullet bullet = new Bullet("bullet"); //new GameObject("bullet");

		//draw bullet at front of player
		Vector offset = new Vector(playerSize.intX(),playerSize.intY()/2-bullet.getSize().intY()/2);
		bullet.moveTo(Vector.add(position, offset));
		bullet.delete(4000);
		
		Game.addGameObject(bullet);
	}
}
