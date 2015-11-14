package scripts;

import java.awt.event.KeyEvent;

import audio.AudioSource;
import misc.Vector;
import components.Animator;
import demo.Bullet;
import framework.Game;
import framework.GameObject;
import framework.Script;

public class PlayerInput implements Script {

	private int speed=10,
			slowSpeed=10, 
			fastSpeed=20;
	private Vector playerSize;
	
	public PlayerInput(Vector playerSize){
		this.playerSize=playerSize;
	}
	
	//take player input and do commands with it
	@Override
	public void execute(GameObject obj) {
		//move player
		Vector direction = Game.getServices().getInput().getDirectionInput();
		Vector displacement = Vector.multiply(direction, new Vector(speed,speed));
		obj.moveBy(displacement.getDeltaVector());
		
		//shoot
		if(Game.getInput().isKeyPressed('P')){
			shoot(obj.getPosition());
		}
		
		//enable boost
		char boostKey = (char)KeyEvent.VK_SPACE;
		if(Game.getInput().isKeyDown(boostKey)){
			speed = fastSpeed;
		}
		else{
			speed = slowSpeed;
		}

		//display animation for boost
		Animator a = (Animator) obj.getComponentByType(Animator.class);
		if(a!=null){
			if(Game.getInput().isKeyDown(boostKey) && a.getCurrentState()!="big"){
					a.setCurrentAnimation("big");
			}
			else if(Game.getServices().getInput().isKeyUp(boostKey)  && a.getCurrentState()!="small"){
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
		GameObject obj = new GameObject("bang");
		obj.add(new AudioSource("bullet.wav"));
		obj.delete(3000);
		Game.addGameObject(obj);
	}
}
